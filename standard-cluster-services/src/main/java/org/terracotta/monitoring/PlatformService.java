/*
 *
 *  The contents of this file are subject to the Terracotta Public License Version
 *  2.0 (the "License"); You may not use this file except in compliance with the
 *  License. You may obtain a copy of the License at
 *
 *  http://terracotta.org/legal/terracotta-public-license.
 *
 *  Software distributed under the License is distributed on an "AS IS" basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 *  the specific language governing rights and limitations under the License.
 *
 *  The Covered Software is Entity API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */
package org.terracotta.monitoring;

import com.tc.classloader.CommonComponent;
import java.io.InputStream;


/**
 * A Service for entities to interact with Platform
 */
@CommonComponent
public interface PlatformService {
  /**
   * Dumps platform state into logs
   */
  void dumpPlatformState();

  /**
   * Stops the server
   */
  void stopPlatform();
  
  /**
   * A Fatal error has occurred.Server will exit.
   * 
   * @param description of the error
   */
  void fatalError(String description);
  /**
   * Server uptime.
   * 
   * @return the milliseconds that have passed since the server process was started
   */
  long uptime();

  /**
   * Provides the raw config file from server startup
   */
  InputStream getPlatformConfiguration();

  /**
   * Stops the server if it is in any of the passive states
   * <p>
   * Based on the {@code restartMode} argument, the server should either bounce or just halt.
   * Currently, it is assumed that the halt may be abrupt and it may not clean up or empty any queues.
   *
   * @param restartMode mode in which the system needs to restart. STOP_ONLY means halt without restarting
   *
   * @throws PlatformStopException when platform stop fails for some reason
   */
  void stopPlatformIfPassive(RestartMode restartMode) throws PlatformStopException;

  /**
   * Stops the server if it is in any of the active states (including active suspended)
   * <p>
   * Based on the {@code restartMode} argument, the server should either bounce or just halt.
   * Currently, it is assumed that the halt may be abrupt and it may not clean up or empty any queues.
   *
   * @param restartMode mode in which the system needs to restart. STOP_ONLY means halt without restarting
   *
   * @throws PlatformStopException when platform stop fails for some reason
   */
  void stopPlatformIfActive(RestartMode restartMode) throws PlatformStopException;

  /**
   * Stops the server.
   * <p>
   * Based on the {@code restartMode} argument, the server should either bounce or just halt.
   * Currently, it is assumed that the halt may be abrupt and it may not clean up or empty any queues.
   *
   * @param restartMode mode in which the system needs to restart. STOP_ONLY means halt without restarting
   */
  void stopPlatform(RestartMode restartMode);

  enum  RestartMode {
    STOP_ONLY, STOP_AND_RESTART
  }
}
