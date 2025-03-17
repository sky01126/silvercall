package com.silvercall.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import static com.silvercall.enums.type.Environment.enumOf;

/**
 * The processing according to the lift cycle of the application.
 *
 * @version 1.0.1
 * @see InitializingBean
 * @see DisposableBean
 */
@Slf4j
@Configuration
public class LifecycleConfiguration implements InitializingBean, DisposableBean {

	@Value("${spring.application.name:NULL}")
	private String applicationName;

	@Value("${info.app.version:NULL}")
	private String applicationVersion;

	@Value("${spring.profiles.active:local}")
	private String activeProfile;

	/**
	 * Add content to handle when spring boot start.
	 */
	@Override
	public void afterPropertiesSet() {
		log.info("[ START ] Add content to handle when spring boot start.");

		StringBuilder appInfo = new StringBuilder();
		appInfo.append("+---------------------+-------------------------------------------").append("\n");
		appInfo.append("| Application Name    | ").append(this.applicationName).append("\n");
		appInfo.append("+---------------------+-------------------------------------------").append("\n");
		appInfo.append("| Application Version | ").append(this.applicationVersion).append("\n");
		appInfo.append("+---------------------+-------------------------------------------").append("\n");
		if (enumOf(this.activeProfile) != null) {
			appInfo.append("| Active Profiles     | ").append(enumOf(this.activeProfile).message()).append("\n");
		} else {
			appInfo.append("| Active Profiles     | ").append(this.activeProfile).append("\n");
		}
		appInfo.append("+---------------------+-------------------------------------------");
		log.info("\n{}", appInfo);
	}

	/**
	 * Add content to handle when spring boot stop.
	 */
	@Override
	public void destroy() {
		log.info("[ STOP  ] Add content to handle when spring boot stop.");
	}

}
