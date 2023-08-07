import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080";
axios.defaults.headers.post["Content-Type"] = "application/json";

export const request = (method, url, data, headers) => {
  return axios({
    method: method,
    url: url,
    headers: headers,
    data: data,
  });
};

export const getClients = () => {
  return request("GET", "/client");
};

export const getModules = () => {
  return request("GET", "/module");
};

export const getTickets = (month, year) => {
  let headers = {};
  if (month && year) {
    headers = {
      Month: month,
      Year: year,
    };
  }
  return request("GET", "/ticket", null, headers);
};

export const createTicket = (ticket) => {
  return request("POST", "/ticket", ticket);
};

export const updateTicket = (ticket) => {
  return request("PUT", "/ticket", ticket);
};

export const deleteTicket = (id) => {
  return request("DELETE", `/ticket/${id}`);
};
