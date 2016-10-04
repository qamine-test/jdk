/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.chbnnels.spi;

import jbvb.io.IOException;
import jbvb.nio.chbnnels.*;


/**
 * Bbse implementbtion clbss for selectbble chbnnels.
 *
 * <p> This clbss defines methods thbt hbndle the mechbnics of chbnnel
 * registrbtion, deregistrbtion, bnd closing.  It mbintbins the current
 * blocking mode of this chbnnel bs well bs its current set of selection keys.
 * It performs bll of the synchronizbtion required to implement the {@link
 * jbvb.nio.chbnnels.SelectbbleChbnnel} specificbtion.  Implementbtions of the
 * bbstrbct protected methods defined in this clbss need not synchronize
 * bgbinst other threbds thbt might be engbged in the sbme operbtions.  </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor Mike McCloskey
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public bbstrbct clbss AbstrbctSelectbbleChbnnel
    extends SelectbbleChbnnel
{

    // The provider thbt crebted this chbnnel
    privbte finbl SelectorProvider provider;

    // Keys thbt hbve been crebted by registering this chbnnel with selectors.
    // They bre sbved becbuse if this chbnnel is closed the keys must be
    // deregistered.  Protected by keyLock.
    //
    privbte SelectionKey[] keys = null;
    privbte int keyCount = 0;

    // Lock for key set bnd count
    privbte finbl Object keyLock = new Object();

    // Lock for registrbtion bnd configureBlocking operbtions
    privbte finbl Object regLock = new Object();

    // Blocking mode, protected by regLock
    boolebn blocking = true;

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @pbrbm  provider
     *         The provider thbt crebted this chbnnel
     */
    protected AbstrbctSelectbbleChbnnel(SelectorProvider provider) {
        this.provider = provider;
    }

    /**
     * Returns the provider thbt crebted this chbnnel.
     *
     * @return  The provider thbt crebted this chbnnel
     */
    public finbl SelectorProvider provider() {
        return provider;
    }


    // -- Utility methods for the key set --

    privbte void bddKey(SelectionKey k) {
        bssert Threbd.holdsLock(keyLock);
        int i = 0;
        if ((keys != null) && (keyCount < keys.length)) {
            // Find empty element of key brrby
            for (i = 0; i < keys.length; i++)
                if (keys[i] == null)
                    brebk;
        } else if (keys == null) {
            keys =  new SelectionKey[3];
        } else {
            // Grow key brrby
            int n = keys.length * 2;
            SelectionKey[] ks =  new SelectionKey[n];
            for (i = 0; i < keys.length; i++)
                ks[i] = keys[i];
            keys = ks;
            i = keyCount;
        }
        keys[i] = k;
        keyCount++;
    }

    privbte SelectionKey findKey(Selector sel) {
        synchronized (keyLock) {
            if (keys == null)
                return null;
            for (int i = 0; i < keys.length; i++)
                if ((keys[i] != null) && (keys[i].selector() == sel))
                    return keys[i];
            return null;
        }
    }

    void removeKey(SelectionKey k) {                    // pbckbge-privbte
        synchronized (keyLock) {
            for (int i = 0; i < keys.length; i++)
                if (keys[i] == k) {
                    keys[i] = null;
                    keyCount--;
                }
            ((AbstrbctSelectionKey)k).invblidbte();
        }
    }

    privbte boolebn hbveVblidKeys() {
        synchronized (keyLock) {
            if (keyCount == 0)
                return fblse;
            for (int i = 0; i < keys.length; i++) {
                if ((keys[i] != null) && keys[i].isVblid())
                    return true;
            }
            return fblse;
        }
    }


    // -- Registrbtion --

    public finbl boolebn isRegistered() {
        synchronized (keyLock) {
            return keyCount != 0;
        }
    }

    public finbl SelectionKey keyFor(Selector sel) {
        return findKey(sel);
    }

    /**
     * Registers this chbnnel with the given selector, returning b selection key.
     *
     * <p>  This method first verifies thbt this chbnnel is open bnd thbt the
     * given initibl interest set is vblid.
     *
     * <p> If this chbnnel is blrebdy registered with the given selector then
     * the selection key representing thbt registrbtion is returned bfter
     * setting its interest set to the given vblue.
     *
     * <p> Otherwise this chbnnel hbs not yet been registered with the given
     * selector, so the {@link AbstrbctSelector#register register} method of
     * the selector is invoked while holding the bppropribte locks.  The
     * resulting key is bdded to this chbnnel's key set before being returned.
     * </p>
     *
     * @throws  ClosedSelectorException {@inheritDoc}
     *
     * @throws  IllegblBlockingModeException {@inheritDoc}
     *
     * @throws  IllegblSelectorException {@inheritDoc}
     *
     * @throws  CbncelledKeyException {@inheritDoc}
     *
     * @throws  IllegblArgumentException {@inheritDoc}
     */
    public finbl SelectionKey register(Selector sel, int ops,
                                       Object btt)
        throws ClosedChbnnelException
    {
        synchronized (regLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if ((ops & ~vblidOps()) != 0)
                throw new IllegblArgumentException();
            if (blocking)
                throw new IllegblBlockingModeException();
            SelectionKey k = findKey(sel);
            if (k != null) {
                k.interestOps(ops);
                k.bttbch(btt);
            }
            if (k == null) {
                // New registrbtion
                synchronized (keyLock) {
                    if (!isOpen())
                        throw new ClosedChbnnelException();
                    k = ((AbstrbctSelector)sel).register(this, ops, btt);
                    bddKey(k);
                }
            }
            return k;
        }
    }


    // -- Closing --

    /**
     * Closes this chbnnel.
     *
     * <p> This method, which is specified in the {@link
     * AbstrbctInterruptibleChbnnel} clbss bnd is invoked by the {@link
     * jbvb.nio.chbnnels.Chbnnel#close close} method, in turn invokes the
     * {@link #implCloseSelectbbleChbnnel implCloseSelectbbleChbnnel} method in
     * order to perform the bctubl work of closing this chbnnel.  It then
     * cbncels bll of this chbnnel's keys.  </p>
     */
    protected finbl void implCloseChbnnel() throws IOException {
        implCloseSelectbbleChbnnel();
        synchronized (keyLock) {
            int count = (keys == null) ? 0 : keys.length;
            for (int i = 0; i < count; i++) {
                SelectionKey k = keys[i];
                if (k != null)
                    k.cbncel();
            }
        }
    }

    /**
     * Closes this selectbble chbnnel.
     *
     * <p> This method is invoked by the {@link jbvb.nio.chbnnels.Chbnnel#close
     * close} method in order to perform the bctubl work of closing the
     * chbnnel.  This method is only invoked if the chbnnel hbs not yet been
     * closed, bnd it is never invoked more thbn once.
     *
     * <p> An implementbtion of this method must brrbnge for bny other threbd
     * thbt is blocked in bn I/O operbtion upon this chbnnel to return
     * immedibtely, either by throwing bn exception or by returning normblly.
     * </p>
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    protected bbstrbct void implCloseSelectbbleChbnnel() throws IOException;


    // -- Blocking --

    public finbl boolebn isBlocking() {
        synchronized (regLock) {
            return blocking;
        }
    }

    public finbl Object blockingLock() {
        return regLock;
    }

    /**
     * Adjusts this chbnnel's blocking mode.
     *
     * <p> If the given blocking mode is different from the current blocking
     * mode then this method invokes the {@link #implConfigureBlocking
     * implConfigureBlocking} method, while holding the bppropribte locks, in
     * order to chbnge the mode.  </p>
     */
    public finbl SelectbbleChbnnel configureBlocking(boolebn block)
        throws IOException
    {
        synchronized (regLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (blocking == block)
                return this;
            if (block && hbveVblidKeys())
                throw new IllegblBlockingModeException();
            implConfigureBlocking(block);
            blocking = block;
        }
        return this;
    }

    /**
     * Adjusts this chbnnel's blocking mode.
     *
     * <p> This method is invoked by the {@link #configureBlocking
     * configureBlocking} method in order to perform the bctubl work of
     * chbnging the blocking mode.  This method is only invoked if the new mode
     * is different from the current mode.  </p>
     *
     * @pbrbm  block  If <tt>true</tt> then this chbnnel will be plbced in
     *                blocking mode; if <tt>fblse</tt> then it will be plbced
     *                non-blocking mode
     *
     * @throws IOException
     *         If bn I/O error occurs
     */
    protected bbstrbct void implConfigureBlocking(boolebn block)
        throws IOException;

}
