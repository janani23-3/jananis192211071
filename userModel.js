import mongoose from 'mongoose';

// Task Schema
const taskSchema = new mongoose.Schema({
    taskName: {
        type: String,
        required: true,
    },
    taskTime: {
        type: String,
        required: true,
    },
    isMorningRoutine: {
        type: Boolean,
        default: false,
    },
});

// User Schema
const userSchema = new mongoose.Schema({
    Name: { 
        type: String, 
        required: true 
    },
    MobileNo: { 
        type: String, 
        required: true 
    },
    password: { 
        type: String, 
        required: true 
    },
    gender: { 
        type: String 
    },
    age: { 
        type: Number 
    },
    wakeUpTime: { 
        type: String 
    },
    tasks: {
        type: [taskSchema],
        default: [
            { taskName: "Wake Up and Hydrate", taskTime: "10 min", isMorningRoutine: true },
            { taskName: "Mindfulness or Meditation", taskTime: "10-15 min", isMorningRoutine: true },
            { taskName: "Exercise", taskTime: "20-30 min", isMorningRoutine: true },
            { taskName: "Personal Development Reading", taskTime: "15-20 min", isMorningRoutine: true },
            { taskName: "Breakfast", taskTime: "15 min", isMorningRoutine: true },
            { taskName: "Planning and Prioritizing", taskTime: "15-20 min", isMorningRoutine: true },
            { taskName: "Creative Work or Learning", taskTime: "20-30 min(optional)", isMorningRoutine: true },
            { taskName: "Read or Learn Something New", taskTime: "15 min", isMorningRoutine: true },
            { taskName: "Affirmations or Visualization", taskTime: "5-10 min", isMorningRoutine: true },
            { taskName: "Light Planning for Long-Term Goals", taskTime: "10 min(optional)", isMorningRoutine: true },
        ],
    },
});

// Export models as named exports
export const User = mongoose.model('User', userSchema);
export const Task = mongoose.model('Task', taskSchema);
