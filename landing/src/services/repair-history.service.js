import httpClient from "../http-common";

const createRepair = (licensePlate, repairTypeId) => {
  return httpClient.post(`/api/v1/repair-history/register/${licensePlate}/${repairTypeId}`);
};

export default { createRepair };
