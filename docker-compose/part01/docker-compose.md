# Docker Compose Command Reference

This document provides a quick reference for commonly used Docker Compose commands.

---

## 1. Check Docker Compose Version

```bash
docker compose version
```

![Check Docker Compose Version](./images/check-version.png)

## 2. Start Services

Start services defined in the `docker-compose.yml` file:

```bash
docker compose up
```

Start services in detached mode (in the background):

![Start Services](./images/start-services.png)

```bash
docker compose up -d
```

![Start Services With D](./images/start-services-d.png)

## 3. View Running Services

List all running services:

```bash
docker compose ps
```

![View Running Services](./images/view-services.png)

## 4. Stop and Remove Services

Stop all running services:

```bash
docker compose down
```

Stop and remove services along with their volumes:

![Stop and Remove Services](./images/stop-remove-services.png)

```bash
docker compose down -v
```

![Stop and Remove Services With V](./images/stop-remove-services-v.png)

## 5. Restart Services

Restart all services:

```bash
docker compose restart
```

![Restart Services](./images/restart-services.png)

## 6. View Logs

Follow logs for all services:

```bash
docker compose logs -f
```

![View Logs](./images/view-logs.png)

## 7. Build Services

Build or rebuild services:

```bash
docker compose build
```

![Build Services](./images/build-services.png)

Build and start services in detached mode:

```bash
docker compose up -d --build
```

![Build Services](./images/build-services-d.png)

## 8. Execute Commands in a Service

Run a command inside a specific service container:

```bash
docker compose exec web bash
```

![Execute Commands in a Service](./images/exec-command.png)

## 9. Run a One-Off Command

Run a one-off command in a new container for a service:

```bash
docker compose run web echo "Hello, World!"
```

![Run a One-Off Command](./images/run-oneoff-command.png)

## 10. Stop a Specific Service

Stop a specific service:

```bash
docker compose stop web
```

![Stop a Specific Service](./images/stop-specific-service.png)

## 11. Remove a Specific Service

Remove a specific service:

```bash
docker compose rm web
```

![Remove a Specific Service](./images/remove-specific-service.png)

## 12. Validate Configuration

Validate the `docker-compose.yml` file:

```bash
docker compose config
```

![Validate Configuration](./images/validate-config.png)

---
