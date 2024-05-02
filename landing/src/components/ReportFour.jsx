import React, { useEffect, useState } from "react";
import repairHistoryService from "../services/repair-history.service";
import { DataGrid } from "@mui/x-data-grid";
import repairService from "../services/repair.service";
import { Typography } from "@mui/material";

function ReportFour() {
  const [repairTypes, setRepairTypes] = useState([]);
  const [rows, setRows] = useState([]);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await repairHistoryService.getReport4();
        const data = response.data;
        const formattedRows = data.map((row) => {
          return {
            id: `${row[0]}`,
            name: row[0],
            gas: row[1],
            diesel: row[2],
            hybrid: row[3],
            electric: row[4],
            totalAmount: row[5],
          };
        });
        setRows(formattedRows);
      } catch (error) {
        console.error("Error al obtener los datos:", error);
      }
    };
    fetchData();
  }, []);

  const columns = [
    { field: "name", headerName: "Reparación", flex: 1 },
    {
      field: "gas",
      headerName: "Cantidad de autos a gas",
      type: "number",
      flex: 1,
    },
    {
      field: "diesel",
      headerName: "Cantidad de autos a diesel",
      type: "number",
      flex: 1,
    },
    {
      field: "hybrid",
      headerName: "Cantidad de autos híbridos",
      type: "number",
      flex: 1,
    },
    {
      field: "electric",
      headerName: "Cantidad de autos eléctricos",
      type: "number",
      flex: 1,
    },
    { field: "totalAmount", headerName: "Monto total", type: "number", flex: 1 },
  ];

  return (
    <div style={{ height: 500, width: "100%" }}>
      <Typography variant="h4" gutterBottom>
        Cantidad de reparaciones por tipo de auto
      </Typography>
      <DataGrid
        rows={rows}
        columns={columns}
        pageSize={10}
        rowsPerPageOptions={[10]}
      />
    </div>
  );
}

export default ReportFour;
