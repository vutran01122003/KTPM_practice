const express = require("express");
const app = express();
const port = 3000;
const DatabaseConnection = require("./database.connection");

const connection01 = new DatabaseConnection("MONGODB").getConnection();
const connection02 = new DatabaseConnection("REDIS").getConnection();
const connection03 = new DatabaseConnection("MYSQL").getConnection();

app.get("/", (req, res) => {
    res.send("Hello World!");
});

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`);
});
