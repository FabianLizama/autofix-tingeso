import { FormControl, MenuItem, Select, TextField, InputLabel, Container, Box, Typography, Button, Alert } from "@mui/material";
import { useState } from "react";
import carService from "../services/car.service";

function RegisterCar() {
  // Estados para cada campo del formulario
  const [formData, setFormData] = useState({
    nameOwner: "",
    rut: "",
    licensePlate: "",
    brand: "",
    model: "",
    type: "",
    fabYear: "",
    motorType: "",
    numSeats: "",
  });

  const [alert, setAlert] = useState({ show: false, message: "" });

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
      const response = await carService.create(formData);
      console.log(response);
      setAlert({
        show: true,
        message: "El vehículo ha sido registrado exitosamente.",
      });
    } catch (error) {
      console.error("Error al registrar el vehículo:", error);
      setAlert({
        show: true,
        message: "Error al registrar el vehículo. Intente nuevamente.",
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
          Registrar vehículo
        </Typography>
        <Typography variant="h6" component="h2" gutterBottom>
          Datos del dueño
        </Typography>
        <Box
          sx={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: 2 }}
          className="mb-4"
        >
          <TextField
            name="nameOwner"
            label="Nombre"
            variant="outlined"
            margin="normal"
            value={formData.nameOwner}
            onChange={handleChange}
          />
          <TextField
            name="rut"
            label="Rut"
            variant="outlined"
            margin="normal"
            value={formData.rut}
            onChange={handleChange}
          />
        </Box>
        <Typography variant="h6" component="h2" gutterBottom>
          Datos del vehículo
        </Typography>
        <TextField
          name="licensePlate"
          label="Patente"
          variant="outlined"
          margin="normal"
          fullWidth
          value={formData.licensePlate}
          onChange={handleChange}
        />
        <TextField
          name="brand"
          label="Marca"
          variant="outlined"
          margin="normal"
          fullWidth
          value={formData.brand}
          onChange={handleChange}
        />
        <TextField
          name="model"
          label="Modelo"
          variant="outlined"
          margin="normal"
          fullWidth
          value={formData.model}
          onChange={handleChange}
        />
        <FormControl variant="outlined" fullWidth margin="normal">
          <InputLabel id="type-label">Tipo de auto</InputLabel>
          <Select
            name="type"
            labelId="type-label"
            id="type-select"
            label="Tipo de auto"
            className="text-start"
            value={formData.type}
            onChange={handleChange}
          >
            <MenuItem value={"sedan"}>Sedan</MenuItem>
            <MenuItem value={"hatch"}>Hatchback</MenuItem>
            <MenuItem value={"suv"}>SUV</MenuItem>
            <MenuItem value={"pickup"}>Pickup</MenuItem>
            <MenuItem value={"van"}>Furgoneta</MenuItem>
          </Select>
        </FormControl>
        <TextField
          name="fabYear"
          label="Año"
          variant="outlined"
          margin="normal"
          fullWidth
          value={formData.fabYear}
          onChange={handleChange}
        />
        <FormControl variant="outlined" fullWidth margin="normal">
          <InputLabel id="motor-label">Tipo de motor</InputLabel>
          <Select
            name="motorType"
            labelId="motor-label"
            id="motor-select"
            label="Tipo de motor"
            className="text-start"
            value={formData.motorType}
            onChange={handleChange}
          >
            <MenuItem value={"gasoline"}>Gasolina</MenuItem>
            <MenuItem value={"diesel"}>Diésel</MenuItem>
            <MenuItem value={"hybrid"}>Híbrido</MenuItem>
            <MenuItem value={"electric"}>Eléctrico</MenuItem>
          </Select>
        </FormControl>
        <TextField
          name="numSeats"
          label="Número de asientos"
          variant="outlined"
          margin="normal"
          fullWidth
          value={formData.numSeats}
          onChange={handleChange}
        />
        <Button type="submit" variant="contained" color="primary">
          Registrar
        </Button>
      </form>
    </Container>
  );
}

export default RegisterCar;
