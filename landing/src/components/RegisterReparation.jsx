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
} from "@mui/material";
import { useState, useEffect } from "react";
import repairService from "../services/repair.service";
import repairHistoryService from "../services/repair-history.service";

function RegisterReparation() {
  const [repairTypes, setRepairTypes] = useState([]);
  
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
    const response = await repairHistoryService.createRepair(formData.licensePlate, formData.repairTypeId);
    console.log(response);
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
          Registrar
        </Button>
      </form>
    </Container>
  );
}

export default RegisterReparation;
