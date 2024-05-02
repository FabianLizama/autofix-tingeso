import {
  FormControl,
  MenuItem,
  Select,
  TextField,
  InputLabel,
  Container,
  Box,
  Typography,
  Button,
  Alert
} from "@mui/material";
import { useState, useEffect } from "react";
import repairService from "../services/repair.service";
import repairHistoryService from "../services/repair-history.service";

function RegisterReparation() {
  const [repairTypes, setRepairTypes] = useState([]);
  const [alert, setAlert] = useState({ show: false, message: "" });
  
  // Se obtienen los tipos de reparaciones de la base de datos
  useEffect(() => {
    repairService.getAll().then((response) => {
      setRepairTypes(response.data);
    });
  }, []);

  // Estados para las reparaciones


  // Estados para cada campo del formulario
  const [formData, setFormData] = useState({
    licensePlate: "",
    repairTypeId: "",
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await repairHistoryService.createRepair(
        formData.licensePlate,
        formData.repairTypeId
      );
      console.log(response);
      const totalAmount = response.data.totalAmount;
      const message = `Reparación registrada con éxito.\n Total calculado: $${totalAmount}.\n Fórmula: Total = Precio: $${response.data.cost} - Recargos: $${response.data.rechargues} + Descuentos: $${response.data.discount} + IVA.`;
      setAlert({ show: true, message: message });
    } catch (error) {
      console.error("Error al registrar la reparación:", error);
      setAlert({
        show: true,
        message: "Error al registrar la reparación. Intente nuevamente.",
      });
    }
  };

  return (
    <Container
      maxWidth="sm"
      style={{
        marginTop: "50px",
        padding: "20px",
        borderRadius: "8px",
      }}
    >
      <form onSubmit={handleSubmit}>
        {alert.show && (
          <Alert severity="success" style={{ marginBottom: "20px" }}>
            {alert.message}
          </Alert>
        )}
        <Typography variant="h4" component="h1" gutterBottom>
          Registrar reparación
        </Typography>
        <TextField
          name="licensePlate"
          label="Patente del auto"
          variant="outlined"
          margin="normal"
          fullWidth
          value={formData.licensePlate}
          onChange={handleChange}
        />
        <FormControl
          variant="outlined"
          fullWidth
          margin="normal"
          style={{ marginBottom: "20px" }}
        >
          <InputLabel
            id="type-label"
            style={{ backgroundColor: "white", padding: "0 4px" }}
          >
            Tipo de reparación
          </InputLabel>
          <Select
            name="repairTypeId"
            labelId="type-label"
            id="type-select"
            label="Tipo de auto"
            className="text-start"
            value={formData.type}
            onChange={handleChange}
          >
            {repairTypes.map((type) => (
              <MenuItem key={type.id} value={type.id}>
                {type.name}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
        <Button type="submit" variant="contained" color="primary">
          Registrar reparación
        </Button>
      </form>
    </Container>
  );
}

export default RegisterReparation;
