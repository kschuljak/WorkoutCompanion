import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    profile: {
      customerId: '',
      name: '',
      photo: ''
    },
    customerList: [],
    exerciseList: [],
    exercise: {
      id: null,
      name: '',
      target: '',
      bodyPart: '',
      equipment: '',
      gifUrl: ''
    },
    metricsList: [],
    metric: {
      id: null,
      customerId: null,
      exerciseId: null,
      date: null,
      reps: 0,
      weight: 0,
      time: 0,
      distance: 0,
      days: 0,
      misc: ''
    },
    gymLogs: [],
    gymVisit: {
      visitId: null,
      customerId: null,
      checkIn: null,
      checkOut: null
    },
    goalList: [],
    goal: {
      id: null,
      name: '',
      customerId: null,
      exerciseId: null,
      date: null,
      reps: 0,
      weight: 0,
      time: 0,
      distance: 0,
      days: 0,
      misc: '',
      isCompleted: false
    },
    gym: {
      clockIn: null,
      clockOut: null,
      inGym: false
    }
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
      state.profile = {
        customerId: '',
        name: '',
        photo: ''
      }
    },
    SET_PROFILE(state, profile) {
      state.profile = profile;
    },
    LOAD_EXERCISE_LIST(state, exercises) {
      state.exerciseList = exercises;
    },
    SET_CURRENT_EXERCISE(state, exercise) {
      state.exercise = exercise;
    },
    LOAD_METRICS_LIST(state, metrics) {
      state.metricsList = metrics;
    },
    LOAD_GOAL_LIST(state, goals) {
      state.goalList = goals;
    },
    GYM_CLOCK_IN(state, date) {
      state.gym.clockIn = date;
    },
    GYM_CLOCK_OUT(state, date) {
      state.gym.clockOut = date;
    },
    SET_IN_GYM(state, inGym) {
      state.gym.inGym = inGym;
    },
    LOAD_GYM_LOGS(state, gymLogs) {
      state.gymLogs = gymLogs;
    },
    SET_GYM_VISIT_ID(state, visitId) {
      state.gymVisit.visitId = visitId;
    }
  }
})
