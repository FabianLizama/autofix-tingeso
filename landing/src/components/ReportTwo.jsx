import React, { useEffect, useState } from "react";
import repairHistoryService from "../services/repair-history.service";
import { DataGrid } from "@mui/x-data-grid";
import repairService from "../services/repair.service";
import { Typography } from "@mui/material";

function ReportTwo() {
  const [repairTypes, setRepairTypes] = useState([]);
  const [rows, setRows] = useState([]);
  useEffect(() => {
    repairService.getAll().then((response) => {
      setRepairTypes(response.data);
    });
    const fetchData = async () => {
      try {
        const response = await repairHistoryService.getReport2();
        const data = response.data;
        const formattedRows = data.map((row) => {
          return {
            id: `${row[0]}`,
            name: row[0],
            numTypes: row[1],
            totalAmount: row[2],
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
      field: "numTypes",
      headerName: "Cantidad de tipos de auto",
      type: "number",
      flex: 1,
    },
    { field: "totalAmount", headerName: "Monto total", type: "number", flex: 1 },
  ];

  return (
    <div style={{ height: 500, width: "100%" }}>
      <Typography variant="h4" gutterBottom>
        Reparaciones por tipos de auto
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

export default ReportTwo;
