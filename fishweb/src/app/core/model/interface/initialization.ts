export interface Initialization {

    /**
     * 设置当前运行环境
     * @param RunningEnvioment：设置运行环境
     */
    setRunningEnviroment(RunningEnvioment: RunningEnvioment);
}

export class RunningEnvioment {
    constructor(
        // 当前环境
        public environmentEnum: EnvironmentEnumModel
    ) {}
}

/**
 * 环境类型
 * @author:admin
 */
export enum EnvironmentEnumModel {
    // 开发环境：连接的后台为自己指定的服务（默认）
    // 启动方式：ng serve -c dev
    DEVELOPMENT = 0,
    // 生产环境：连接的后台服务为生产环境的服务
    // 启动方式：ng serve -c prod
    PRODUCTION = 1,
    // 测试环境：连接的后台服务为测试环境的服务
    // 启动方式：ng serve -c test
    TEST = 2,
    // 本地环境：连接的后台服务为本地启动的服务
    // 启动方式：ng serve -c local
    LOCAL = 3,
    // 模拟环境：连接的后台服务为前端模拟的服务
    // 启动方式：ng serve -c mock
    MOCK = 4,
    // 部署环境：连接的后台服务为部署环境的服务
    // 启动方式：ng serve -c dep
    DEPLOY = 5,
}