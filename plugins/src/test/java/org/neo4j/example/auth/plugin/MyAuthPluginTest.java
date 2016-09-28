/**
 * Copyright (c) 2002-2016 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4j.example.auth.plugin;

import org.junit.Test;

import java.nio.file.Paths;
import java.util.Optional;

import org.neo4j.server.security.enterprise.auth.plugin.api.RealmOperations;

import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MyAuthPluginTest
{
    @Test
    public void shouldLogErrorOnNonExistingConfigFile()
    {
        // Given
        MyAuthPlugin plugin = new MyAuthPlugin();
        RealmOperations api = mock( RealmOperations.class );
        RealmOperations.Log log = mock( RealmOperations.Log.class );

        when( api.neo4jHome() ).thenReturn( Paths.get( "" ) );
        when( api.neo4jConfigFile() ).thenReturn( Optional.empty() );
        when( api.log() ).thenReturn( log );

        // When
        plugin.initialize( api );

        // Then
        verify( log ).error( startsWith( "Failed to load config file" ) );
    }
}