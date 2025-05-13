import express from "express";
import {
    fetch,
    createUser,
    loginUser,
    update,
    deleteUser,
    saveGender,
    saveWakeUpTime,
    addTask,
    getProfile,
    editProfile,
    saveAge,
    getMorningTasks,
    deleteTask,
} from "../controller/userController.js";

const route = express.Router();

// Routes for managing user
route.post("/create", createUser);
route.post("/login",loginUser); 
route.get("/getAllUsers", fetch);  // Fetch function from userController.js
route.put("/update/:id", update);
route.delete("/delete/:id", deleteUser);
route.get("/profile/:id", getProfile);
route.put("/editProfile/:id", editProfile);
route.post("/saveAge/:id", saveAge);
route.post("/saveGender/:id", saveGender);
route.post("/saveWakeUpTime/:id", saveWakeUpTime);
route.post("/addTask/:userId", addTask);
route.get("/morningTasks/:userId", getMorningTasks);
route.delete('/deleteTask/:userId/:id', deleteTask);

export default route;
