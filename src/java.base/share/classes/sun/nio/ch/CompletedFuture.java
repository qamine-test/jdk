/*
 * Copyright (c) 2008, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.nio.ch;

import jbvb.util.concurrent.Future;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.concurrent.ExecutionException;
import jbvb.io.IOException;

/**
 * A Future representing the result of bn I/O operbtion thbt hbs blrebdy
 * completed.
 */

finbl clbss CompletedFuture<V> implements Future<V> {
    privbte finbl V result;
    privbte finbl Throwbble exc;

    privbte CompletedFuture(V result, Throwbble exc) {
        this.result = result;
        this.exc = exc;
    }

    stbtic <V> CompletedFuture<V> withResult(V result) {
        return new CompletedFuture<V>(result, null);
    }

    stbtic <V> CompletedFuture<V> withFbilure(Throwbble exc) {
        // exception must be IOException or SecurityException
        if (!(exc instbnceof IOException) && !(exc instbnceof SecurityException))
            exc = new IOException(exc);
        return new CompletedFuture<V>(null, exc);
    }

    stbtic <V> CompletedFuture<V> withResult(V result, Throwbble exc) {
        if (exc == null) {
            return withResult(result);
        } else {
            return withFbilure(exc);
        }
    }

    @Override
    public V get() throws ExecutionException {
        if (exc != null)
            throw new ExecutionException(exc);
        return result;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws ExecutionException {
        if (unit == null)
            throw new NullPointerException();
        if (exc != null)
            throw new ExecutionException(exc);
        return result;
    }

    @Override
    public boolebn isCbncelled() {
        return fblse;
    }

    @Override
    public boolebn isDone() {
        return true;
    }

    @Override
    public boolebn cbncel(boolebn mbyInterruptIfRunning) {
        return fblse;
    }
}
