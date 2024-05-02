import httpClient from "../http-common";

const createRepair = (licensePlate, repairTypeId) => {
  return httpClient.post(`/api/v1/repair-history/register/${licensePlate}/${repairTypeId}`);
};

const getReport1 = () =>
  httpClient.get("/api/v1/repair-history/get-all-repairs-costs");

const getReport2 = () =>
    httpClient.get("/api/v1/repair-history/get-report-2");

const getReport3 = () =>
    httpClient.get("/api/v1/repair-history/get-report-3");

const getReport4 = () =>
    httpClient.get("/api/v1/repair-history/get-report-4");

export default { createRepair, getReport1, getReport2, getReport3, getReport4 };
