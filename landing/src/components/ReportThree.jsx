import React, { useEffect, useState } from "react";
import repairHistoryService from "../services/repair-history.service";
import { DataGrid } from "@mui/x-data-grid";
import repairService from "../services/repair.service";
import { Typography } from "@mui/material";

function ReportThree() {
  const [repairTypes, setRepairTypes] = useState([]);
  const [rows, setRows] = useState([]);
  useEffect(() => {
    const fetchData = async () => {
    try {
        const response = await repairHistoryService.getReport3();
        const data = response.data;
        const formattedRows = data.map((row) => {
            return {
                id: `${row[0]}`,
                brand: row[0],
                time: Math.round(row[1]),
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
    { field: "brand", headerName: "Marca", flex: 1 },
    {
      field: "time",
      headerName: "Horas promedio de reparación",
      type: "number",
      flex: 1,
    },
  ];

  return (
    <div style={{ height: 500, width: "100%" }}>
      <Typography variant="h4" gutterBottom>
        Tiempos promedio de reparación por marca
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

export default ReportThree;
