import express, { type Request, type Response, type NextFunction, type Express } from "express";

const app: Express = express();

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.get("/", (req: Request, res: Response, next: NextFunction) => {
    res.status(200).send("Hello world");
});

export default app;
