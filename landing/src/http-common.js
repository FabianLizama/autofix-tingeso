import axios from "axios";

const backendServer = import.meta.env.VITE_PAYROLL_BACKEND_SERVER;
const backendPort = import.meta.env.VITE_PAYROLL_BACKEND_PORT;

console.log(backendServer);
console.log(backendPort);

export default axios.create({
  baseURL: `http://localhost:8079`,
  headers: {
    "Content-Type": "application/json",
  },
});
