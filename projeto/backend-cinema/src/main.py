from infrastructure.config import ConfigManager, ConfigName

if __name__ == "__main__":
    import uvicorn

    config_manager = ConfigManager()
    server_port = config_manager.get(ConfigName.APP_PORT)
    
    uvicorn.run(
        "main:app",
        host="localhost",
        port=int(server_port),
        reload=True,
        log_level="debug",
        use_colors=True,
    )