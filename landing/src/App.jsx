import {BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import './App.css'
import Home from './components/Home'
import NavBar from './components/NavBar'
import RegisterCar from './components/RegisterCar'
import RegisterReparation from './components/RegisterReparation'
import ReportOne from './components/ReportOne'
import ReportTwo from './components/ReportTwo'
import ReportThree from './components/ReportThree'
import ReportFour from './components/ReportFour'
import NotFound from './components/NotFound'

function App() {
  return (
    <>
      <Router>
        <div>
          <NavBar></NavBar>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/register/car" element={<RegisterCar />} />
            <Route path="/register/reparation" element={<RegisterReparation />} />
            <Route path="/report1" element={<ReportOne />} />
            <Route path="/report2" element={<ReportTwo />} />
            <Route path="/report3" element={<ReportThree />} />
            <Route path="/report4" element={<ReportFour />} />
            <Route path="/*" element={<NotFound />} />
          </Routes>
        </div>
      </Router>
    </>
  )
}

export default App
