import React, { useEffect, useState } from "react";
import repairHistoryService from "../services/repair-history.service";
import { DataGrid } from "@mui/x-data-grid";
import repairService from "../services/repair.service";
import { Typography } from "@mui/material";

function ReportOne() {
  const [repairTypes, setRepairTypes] = useState([]);
  const [rows, setRows] = useState([]);
  useEffect(() => {
    repairService.getAll().then((response) => {
      setRepairTypes(response.data);
    });
    const fetchData = async () => {
      try {
        const response = await repairHistoryService.getReport1();
        const data = response.data;
        const formattedRows = data.map((row) => {
          const repairType = repairTypes.find(
            (repairType) => repairType.id === row[5]
          );
          return {
            id: `${row[0]}-${row[5]}`, // Clave única combinando patente y repair_type_id
            licensePlate: row[0],
            cost: row[1],
            discount: row[2],
            recharges: row[3],
            iva: (row[1]+row[3]-row[2])*0.19,
            totalAmount: row[4],
          };
        });
        setRows(formattedRows);
      } catch (error) {
        console.error('Error al obtener los datos:', error);
      }
    };
    fetchData();
    
  }, []);

  const columns = [
    { field: "licensePlate", headerName: "Patente", flex: 1 },
    { field: "cost", headerName: "Costo", type: "number", flex: 1 },
    { field: "discount", headerName: "Descuento", type: "number", flex: 1 },
    { field: "recharges", headerName: "Recargos", type: "number", flex: 1 },
    {
      field: "iva",
      headerName: "IVA",
      type: "number",
      flex: 1,
    },
    {
      field: "totalAmount",
      headerName: "Monto Total",
      type: "number",
      flex: 1,
    },
  ];

  return (
    <div style={{ height: 500, width: "100%" }}>
      <Typography variant="h4" gutterBottom>
        Valores de reparaciones
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

export default ReportOne;
