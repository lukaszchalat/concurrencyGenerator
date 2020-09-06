package concurrency.generator.backend.code.generator;

import com.squareup.javapoet.ClassName;

public class ClassNameProvider {

	public static final ClassName LOCK = ClassName.get("java.util.concurrent.locks", "Lock");
	public static final ClassName REENTRANTLOCK = ClassName.get("java.util.concurrent.locks", "ReentrantLock");
	public static final ClassName EXECUTOR_SERVICE = ClassName.get("java.util.concurrent", "ExecutorService");
	public static final ClassName EXECUTORS = ClassName.get("java.util.concurrent", "Executors");
	public static final ClassName TASK_EXECUTOR = ClassName.get("org.springframework.scheduling.concurrent", "ThreadPoolTaskExecutor");
}
