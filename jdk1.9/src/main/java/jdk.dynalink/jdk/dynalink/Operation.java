/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 *
 *
 *
 *
 *
 */
/*
   Copyright 2015 Attila Szegedi

   Licensed under both the Apache License, Version 2.0 (the "Apache License")
   and the BSD License (the "BSD License"), with licensee being free to
   choose either of the two at their discretion.

   You may not use this file except in compliance with either the Apache
   License or the BSD License.

   If you choose to use this file in compliance with the Apache License, the
   following notice applies to you:

       You may obtain a copy of the Apache License at

           http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
       implied. See the License for the specific language governing
       permissions and limitations under the License.

   If you choose to use this file in compliance with the BSD License, the
   following notice applies to you:

       Redistribution and use in source and binary forms, with or without
       modification, are permitted provided that the following conditions are
       met:
       * Redistributions of source code must retain the above copyright
         notice, this list of conditions and the following disclaimer.
       * Redistributions in binary form must reproduce the above copyright
         notice, this list of conditions and the following disclaimer in the
         documentation and/or other materials provided with the distribution.
       * Neither the name of the copyright holder nor the names of
         contributors may be used to endorse or promote products derived from
         this software without specific prior written permission.

       THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
       IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
       TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
       PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL COPYRIGHT HOLDER
       BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
       CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
       SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
       BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
       WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
       OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
       ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package jdk.dynalink;

/**
 * An object that describes a dynamic operation. Dynalink defines a set of
 * standard operations with the {@link StandardOperation} class, as well as a
 * way to express the target {@link Namespace namespace(s)} of an operation
 * on an object using {@link NamespaceOperation} and finally a way to attach
 * a fixed target name to an operation using {@link NamedOperation}.
 * When presenting examples in this documentation, we will refer to standard
 * operations using their name (e.g. {@code GET}), to namespace operations
 * by separating their base operation with a colon from their namespace
 * (e.g. {@code GET:PROPERTY}), or in case of multiple namespaces we will
 * further separate those with the vertical line character (e.g.
 * {@code GET:PROPERTY|ELEMENT}), and finally we will refer to named operations
 * by separating the base operation and the name with the colon character (e.g.
 * {@code GET:PROPERTY|ELEMENT:color}).
 */
public interface Operation {
    /**
     * Returns a {@link NamespaceOperation} using this operation as its base.
     * @param namespace the namespace that is the target of the namespace operation.
     * @return a {@link NamespaceOperation} with this operation as its base and the specified
     * namespace as its target.
     * @throws IllegalArgumentException if this operation is already a namespace operation or a named operation.
     * @throws NullPointerException if {@code namespace} is null.
     */
    default NamespaceOperation withNamespace(final Namespace namespace) {
        return withNamespaces(namespace);
    }

    /**
     * Returns a {@link NamespaceOperation} using this operation as its base.
     * @param namespaces the namespaces that are the target of the namespace operation.
     * @return a {@link NamespaceOperation} with this operation as its base and the specified
     * namespaces as its targets.
     * @throws IllegalArgumentException if this operation is already a namespace operation or a named operation.
     * @throws NullPointerException if {@code namespace} or any of its elements is null.
     */
    default NamespaceOperation withNamespaces(final Namespace... namespaces) {
        return new NamespaceOperation(this, namespaces);
    }

    /**
     * Returns a {@link NamedOperation} using this operation as its base.
     * @param name the name that is the target of the named operation.
     * @return a {@link NamedOperation} with this operation as its base and the specified name.
     * @throws IllegalArgumentException if this operation is already a named operation.
     * @throws NullPointerException if {@code name} is null.
     */
    default NamedOperation named(final Object name) {
        return new NamedOperation(this, name);
    }
}
