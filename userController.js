import mongoose from 'mongoose';
import { User } from "../model/userModel.js";

export const fetch = async (req, res) => {
    try {
        const users = await User.find();  // Example, adjust as needed
        res.status(200).json({ success: true, users });
    } catch (error) {
        console.error(error);
        res.status(500).json({ success: false, message: "Error fetching users" });
    }
};

// Create User
export const createUser = async (req, res) => {
    const { Name, MobileNo, gender, wakeUpTime, password } = req.body;

    try {
        // Check if a user with the same MobileNo already exists
        const existingUser = await User.findOne({ MobileNo });

        if (existingUser) {
            // If a user with the same MobileNo is found, return an error response
            return res.status(400).json({
                success: false,
                message: "User with this Mobile Number already exists."
            });
        }

        // If no duplicate, create a new user with the provided data
        const newUser = new User({
            Name, 
            MobileNo, 
            gender, 
            wakeUpTime, 
            password // Store password as it is (without hashing)
        });

        await newUser.save();

        // Respond with success and user details (excluding password)
        res.status(201).json({
            success: true,
            message: "User created successfully",
            user: {
                id: newUser._id,
                Name: newUser.Name,
                MobileNo: newUser.MobileNo,
                gender: newUser.gender,
                wakeUpTime: newUser.wakeUpTime
            }
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({ success: false, message: "Error creating user" });
    }
};

// login user
export const loginUser = async (req, res) => {
    const { MobileNo, password } = req.body;

    if (!MobileNo || !password) {
        return res.status(401).json({
            success: false,
            message: "All fields are required"
        });
    }

    try {
        // Check if a user with the given MobileNo exists
        const existingUser = await User.findOne({ MobileNo });

        if (!existingUser) {
            return res.status(404).json({
                success: false,
                message: "User not found with this Mobile Number."
            });
        }

        // If passwords match, proceed with login
        if (existingUser.password === password) {  // Compare plain passwords
            // Return a successful login response (you might want to use a JWT here)
            res.status(200).json({
                success: true,
                message: "Login successful",
                user: {
                    id: existingUser._id,
                    Name: existingUser.Name,
                    MobileNo: existingUser.MobileNo,
                    gender: existingUser.gender,
                    wakeUpTime: existingUser.wakeUpTime
                }
            });
        } else {
            // If the password doesn't match
            return res.status(401).json({
                success: false,
                message: "Invalid password."
            });
        }

    } catch (error) {
        console.error(error);
        res.status(500).json({ success: false, message: "Error logging in" });
    }
};


// Update User
export const update = async (req, res) => {
    const { id } = req.params;
    const { Name, DOB, MobileNo, gender, wakeUpTime } = req.body;

    if (!mongoose.Types.ObjectId.isValid(id)) {
        return res.status(400).json({ message: "Invalid user ID format" });
    }

    try {
        // Find the user by ID and update the fields provided in the request body
        const updatedUser = await User.findByIdAndUpdate(
            id,
            { Name, DOB, MobileNo, gender, wakeUpTime },
            { new: true } // Return the updated user
        );

        if (!updatedUser) {
            return res.status(404).json({ message: "User not found" });
        }

        res.status(200).json({
            success: true,
            message: "User updated successfully",
            user: updatedUser,
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: "Error updating user" });
    }
};


// Get User Profile (Updated for name, gender, and age)
export const getProfile = async (req, res) => {
    const { id } = req.params;

    if (!mongoose.Types.ObjectId.isValid(id)) {
        return res.status(400).json({ message: "Invalid user ID format" });
    }

    try {
        const user = await User.findById(id).select('Name gender age');  // Select only the fields needed
        if (!user) {
            return res.status(404).json({ message: "User not found" });
        }

        res.status(200).json({
            success: true,
            user,
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({
            message: "Error fetching profile",
        });
    }
};

// Edit Profile (Updated for name, gender, and age)
export const editProfile = async (req, res) => {
    const { id } = req.params;
    const { Name, gender, age } = req.body;  // Only update the required fields

    if (!mongoose.Types.ObjectId.isValid(id)) {
        return res.status(400).json({ message: "Invalid user ID format" });
    }

    try {
        const updatedProfile = await User.findByIdAndUpdate(
            id,
            { Name, gender, age },
            { new: true, select: 'Name gender age' }
        );

        if (!updatedProfile) {
            return res.status(404).json({ message: "User not found" });
        }

        res.status(200).json({
            success: true,
            message: "Profile updated successfully",
            user: updatedProfile,
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: "Error updating profile" });
    }
};

// Save Age
export const saveAge = async (req, res) => {
    const { id } = req.params;
    const { age } = req.body;

    if (!age) {
        return res.status(400).json({ message: "Age is required" });
    }

    try {
        const updatedUser = await User.findByIdAndUpdate(
            id, 
            { $set: { age } }, 
            { new: true }
        );

        if (!updatedUser) {
            return res.status(404).json({ message: "User not found" });
        }

        res.status(200).json({
            success: true,
            message: "Age saved successfully",
            user: updatedUser,
        });
    } catch (error) {
        console.error("Error saving age:", error);
        res.status(500).json({ message: "Error saving age" });
    }
};

// Save Gender
export const saveGender = async (req, res) => {
    const { id } = req.params;
    const { gender } = req.body;

    try {
        const updatedUser = await User.findByIdAndUpdate(id, { gender }, { new: true });
        res.status(200).json({
            success: true,
            message: "Gender saved successfully",
            user: updatedUser,
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: "Error saving gender" });
    }
};

// Save Wake Up Time
export const saveWakeUpTime = async (req, res) => {
    const { id } = req.params;
    const { wakeUpTime } = req.body;

    try {
        const updatedUser = await User.findByIdAndUpdate(id, { wakeUpTime }, { new: true });
        res.status(200).json({
            success: true,
            message: "Wake-up time saved successfully",
            user: updatedUser,
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: "Error saving wake-up time" });
    }
};

// Add Task
export const addTask = async (req, res) => {
    const { userId } = req.params;
    const { taskName, taskTime, isMorningRoutine } = req.body;

    try {
        const user = await User.findById(userId);
        if (!user) return res.status(404).json({ message: 'User not found' });

        user.tasks.push({ taskName, taskTime, isMorningRoutine: isMorningRoutine || false });
        await user.save();

        res.status(200).json({
            success: true,
            message: 'Task added successfully',
            tasks: user.tasks,
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: 'Error adding task' });
    }
};

// Fetch Morning Tasks
export const getMorningTasks = async (req, res) => {
    const { userId } = req.params;

    try {
        const user = await User.findById(userId).select("tasks");
        if (!user) return res.status(404).json({ message: "User not found" });

        const morningTasks = user.tasks.filter(task => task.isMorningRoutine);
        res.status(200).json({
            success: true,
            tasks: morningTasks,
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: "Error fetching morning tasks" });
    }
};

// Delete User
export const deleteUser = async (req, res) => {
    const { id } = req.params;

    try {
        const deletedUser = await User.findByIdAndDelete(id);

        if (!deletedUser) {
            return res.status(404).json({
                success: false,
                message: "User not found",
            });
        }

        res.status(200).json({
            success: true,
            message: "User deleted successfully",
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({
            success: false,
            message: "Error deleting user",
        });
    }
};

// Delete Task
export const deleteTask = async (req, res) => {
    const { userId, id: taskId } = req.params;

    try {
        const user = await User.findById(userId);
        if (!user) return res.status(404).json({ message: 'User not found' });

        // Find task by taskId in the user's tasks array
        const taskIndex = user.tasks.findIndex((task) => task._id.toString() === taskId);
        
        // If task not found, return 404
        if (taskIndex === -1) return res.status(404).json({ message: 'Task not found' });

        // Remove task from the array
        user.tasks.splice(taskIndex, 1);
        await user.save();  // Save the updated user

        res.status(200).json({ message: 'Task deleted successfully' });
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: 'Error deleting task' });
    }
};
