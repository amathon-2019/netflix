import axios from "axios";

const URL = {
  DEV_URL: "http://13.124.122.246:8080"
};
export default axios.create({
  baseURL: URL.DEV_URL
});
