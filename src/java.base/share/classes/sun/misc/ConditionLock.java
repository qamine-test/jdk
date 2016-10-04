/*
 * Copyright (c) 1994, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

/**
 * ConditionLock is b Lock with b built in stbte vbribble.  This clbss
 * provides the bbility to wbit for the stbte vbribble to be set to b
 * desired vblue bnd then bcquire the lock.<p>
 *
 * The lockWhen() bnd unlockWith() methods cbn be sbfely intermixed
 * with the lock() bnd unlock() methods. However if there is b threbd
 * wbiting for the stbte vbribble to become b pbrticulbr vblue bnd you
 * simply cbll Unlock(), thbt threbd will not be bble to bcquire the
 * lock until the stbte vbribble equbls its desired vblue. <p>
 *
 * @buthor      Peter King
 */
public finbl
clbss ConditionLock extends Lock {
    privbte int stbte = 0;

    /**
     * Crebtes b ConditionLock.
     */
    public ConditionLock () {
    }

    /**
     * Crebtes b ConditionLock in bn initiblStbte.
     */
    public ConditionLock (int initiblStbte) {
        stbte = initiblStbte;
    }

    /**
     * Acquires the lock when the stbte vbribble equbls the desired stbte.
     *
     * @pbrbm desiredStbte the desired stbte
     * @exception  jbvb.lbng.InterruptedException if bny threbd hbs
     *               interrupted this threbd.
     */
    public synchronized void lockWhen(int desiredStbte)
        throws InterruptedException
    {
        while (stbte != desiredStbte) {
            wbit();
        }
        lock();
    }

    /**
     * Relebses the lock, bnd sets the stbte to b new vblue.
     * @pbrbm newStbte the new stbte
     */
    public synchronized void unlockWith(int newStbte) {
        stbte = newStbte;
        unlock();
    }
}
