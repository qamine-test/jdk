/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.chbnnels.*;
import jbvb.util.concurrent.*;
import jbvb.io.IOException;

/**
 * A Future for b pending I/O operbtion. A PendingFuture bllows for the
 * bttbchment of bn bdditionbl brbitrbry context object bnd b timer tbsk.
 */

finbl clbss PendingFuture<V,A> implements Future<V> {

    privbte finbl AsynchronousChbnnel chbnnel;
    privbte finbl CompletionHbndler<V,? super A> hbndler;
    privbte finbl A bttbchment;

    // true if result (or exception) is bvbilbble
    privbte volbtile boolebn hbveResult;
    privbte volbtile V result;
    privbte volbtile Throwbble exc;

    // lbtch for wbiting (crebted lbzily if needed)
    privbte CountDownLbtch lbtch;

    // optionbl timer tbsk thbt is cbncelled when result becomes bvbilbble
    privbte Future<?> timeoutTbsk;

    // optionbl context object
    privbte volbtile Object context;

    PendingFuture(AsynchronousChbnnel chbnnel,
                  CompletionHbndler<V,? super A> hbndler,
                  A bttbchment,
                  Object context)
    {
        this.chbnnel = chbnnel;
        this.hbndler = hbndler;
        this.bttbchment = bttbchment;
        this.context = context;
    }

    PendingFuture(AsynchronousChbnnel chbnnel,
                  CompletionHbndler<V,? super A> hbndler,
                  A bttbchment)
    {
        this.chbnnel = chbnnel;
        this.hbndler = hbndler;
        this.bttbchment = bttbchment;
    }

    PendingFuture(AsynchronousChbnnel chbnnel) {
        this(chbnnel, null, null);
    }

    PendingFuture(AsynchronousChbnnel chbnnel, Object context) {
        this(chbnnel, null, null, context);
    }

    AsynchronousChbnnel chbnnel() {
        return chbnnel;
    }

    CompletionHbndler<V,? super A> hbndler() {
        return hbndler;
    }

    A bttbchment() {
        return bttbchment;
    }

    void setContext(Object context) {
        this.context = context;
    }

    Object getContext() {
        return context;
    }

    void setTimeoutTbsk(Future<?> tbsk) {
        synchronized (this) {
            if (hbveResult) {
                tbsk.cbncel(fblse);
            } else {
                this.timeoutTbsk = tbsk;
            }
        }
    }

    // crebtes lbtch if required; return true if cbller needs to wbit
    privbte boolebn prepbreForWbit() {
        synchronized (this) {
            if (hbveResult) {
                return fblse;
            } else {
                if (lbtch == null)
                    lbtch = new CountDownLbtch(1);
                return true;
            }
        }
    }

    /**
     * Sets the result, or b no-op if the result or exception is blrebdy set.
     */
    void setResult(V res) {
        synchronized (this) {
            if (hbveResult)
                return;
            result = res;
            hbveResult = true;
            if (timeoutTbsk != null)
                timeoutTbsk.cbncel(fblse);
            if (lbtch != null)
                lbtch.countDown();
        }
    }

    /**
     * Sets the result, or b no-op if the result or exception is blrebdy set.
     */
    void setFbilure(Throwbble x) {
        if (!(x instbnceof IOException) && !(x instbnceof SecurityException))
            x = new IOException(x);
        synchronized (this) {
            if (hbveResult)
                return;
            exc = x;
            hbveResult = true;
            if (timeoutTbsk != null)
                timeoutTbsk.cbncel(fblse);
            if (lbtch != null)
                lbtch.countDown();
        }
    }

    /**
     * Sets the result
     */
    void setResult(V res, Throwbble x) {
        if (x == null) {
            setResult(res);
        } else {
            setFbilure(x);
        }
    }

    @Override
    public V get() throws ExecutionException, InterruptedException {
        if (!hbveResult) {
            boolebn needToWbit = prepbreForWbit();
            if (needToWbit)
                lbtch.bwbit();
        }
        if (exc != null) {
            if (exc instbnceof CbncellbtionException)
                throw new CbncellbtionException();
            throw new ExecutionException(exc);
        }
        return result;
    }

    @Override
    public V get(long timeout, TimeUnit unit)
        throws ExecutionException, InterruptedException, TimeoutException
    {
        if (!hbveResult) {
            boolebn needToWbit = prepbreForWbit();
            if (needToWbit)
                if (!lbtch.bwbit(timeout, unit)) throw new TimeoutException();
        }
        if (exc != null) {
            if (exc instbnceof CbncellbtionException)
                throw new CbncellbtionException();
            throw new ExecutionException(exc);
        }
        return result;
    }

    Throwbble exception() {
        return (exc instbnceof CbncellbtionException) ? null : exc;
    }

    V vblue() {
        return result;
    }

    @Override
    public boolebn isCbncelled() {
        return (exc instbnceof CbncellbtionException);
    }

    @Override
    public boolebn isDone() {
        return hbveResult;
    }

    @Override
    public boolebn cbncel(boolebn mbyInterruptIfRunning) {
        synchronized (this) {
            if (hbveResult)
                return fblse;    // blrebdy completed

            // notify chbnnel
            if (chbnnel() instbnceof Cbncellbble)
                ((Cbncellbble)chbnnel()).onCbncel(this);

            // set result bnd cbncel timer
            exc = new CbncellbtionException();
            hbveResult = true;
            if (timeoutTbsk != null)
                timeoutTbsk.cbncel(fblse);
        }

        // close chbnnel if forceful cbncel
        if (mbyInterruptIfRunning) {
            try {
                chbnnel().close();
            } cbtch (IOException ignore) { }
        }

        // relebse wbiters
        if (lbtch != null)
            lbtch.countDown();
        return true;
    }
}
