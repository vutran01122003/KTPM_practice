const mongoose = require("mongoose");
const Redis = require("ioredis");
const mysql = require("mysql2/promise");

class DatabaseConnection {
    static instance;
    connection;

    constructor(databaseType) {
        this.onConnect(databaseType);
    }

    static getInstance() {
        if (!MongoDB.instance) MongoDB.instance = new MongoDB();

        return MongoDB.instance;
    }

    async onConnect(databaseType) {
        try {
            switch (databaseType) {
                case "MONGODB": {
                    this.connection = mongoose.createConnection("mongodb://localhost:27017/test");

                    this.connection.on("connected", () => console.log(`MONGODB: ${this.connection.name} connected`));
                    this.connection.on("error", (error) => {
                        throw error;
                    });
                    this.connection.on("disconnected", () =>
                        console.log(`MONGODB: ${this.connection.name} disconnected`)
                    );
                    break;
                }

                case "REDIS": {
                    this.connection = new Redis({
                        port: 6379,
                        host: "127.0.0.1"
                    });

                    this.connection.on("connect", () => {
                        console.log(`REDIS connected`);
                    });
                    break;
                }

                case "MYSQL": {
                    this.connection = mysql
                        .createConnection({
                            host: "localhost",
                            user: "root",
                            password: "123456a",
                            database: "payment-service"
                        })
                        .then(() => {
                            console.log("MYSQL connected");
                        });
                    break;
                }

                default: {
                    console.log("Database type does not support");
                    break;
                }
            }
        } catch (error) {
            throw error;
        }
    }

    getConnection() {
        return this.connection;
    }
}

module.exports = DatabaseConnection;
