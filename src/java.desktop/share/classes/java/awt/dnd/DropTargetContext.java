/*
 * Copyright (c) 1997, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dnd;

import jbvb.bwt.Component;

import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.dbtbtrbnsfer.UnsupportedFlbvorException;

import jbvb.bwt.dnd.peer.DropTbrgetContextPeer;

import jbvb.io.IOException;
import jbvb.io.Seriblizbble;

import jbvb.util.Arrbys;
import jbvb.util.List;


/**
 * A <code>DropTbrgetContext</code> is crebted
 * whenever the logicbl cursor bssocibted
 * with b Drbg bnd Drop operbtion coincides with the visible geometry of
 * b <code>Component</code> bssocibted with b <code>DropTbrget</code>.
 * The <code>DropTbrgetContext</code> provides
 * the mechbnism for b potentibl receiver
 * of b drop operbtion to both provide the end user with the bppropribte
 * drbg under feedbbck, but blso to effect the subsequent dbtb trbnsfer
 * if bppropribte.
 *
 * @since 1.2
 */

public clbss DropTbrgetContext implements Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -634158968993743371L;

    /**
     * Construct b <code>DropTbrgetContext</code>
     * given b specified <code>DropTbrget</code>.
     *
     * @pbrbm dt the DropTbrget to bssocibte with
     */

    DropTbrgetContext(DropTbrget dt) {
        super();

        dropTbrget = dt;
    }

    /**
     * This method returns the <code>DropTbrget</code> bssocibted with this
     * <code>DropTbrgetContext</code>.
     *
     * @return the <code>DropTbrget</code> bssocibted with this <code>DropTbrgetContext</code>
     */

    public DropTbrget getDropTbrget() { return dropTbrget; }

    /**
     * This method returns the <code>Component</code> bssocibted with
     * this <code>DropTbrgetContext</code>.
     *
     * @return the Component bssocibted with this Context
     */

    public Component getComponent() { return dropTbrget.getComponent(); }

    /**
     * Cblled when bssocibted with the <code>DropTbrgetContextPeer</code>.
     *
     * @pbrbm dtcp the <code>DropTbrgetContextPeer</code>
     */

    public void bddNotify(DropTbrgetContextPeer dtcp) {
        dropTbrgetContextPeer = dtcp;
    }

    /**
     * Cblled when disbssocibted with the <code>DropTbrgetContextPeer</code>.
     */

    public void removeNotify() {
        dropTbrgetContextPeer = null;
        trbnsferbble          = null;
    }

    /**
     * This method sets the current bctions bcceptbble to
     * this <code>DropTbrget</code>.
     *
     * @pbrbm bctions bn <code>int</code> representing the supported bction(s)
     */

    protected void setTbrgetActions(int bctions) {
        DropTbrgetContextPeer peer = getDropTbrgetContextPeer();
        if (peer != null) {
            synchronized (peer) {
                peer.setTbrgetActions(bctions);
                getDropTbrget().doSetDefbultActions(bctions);
            }
        } else {
            getDropTbrget().doSetDefbultActions(bctions);
        }
    }

    /**
     * This method returns bn <code>int</code> representing the
     * current bctions this <code>DropTbrget</code> will bccept.
     *
     * @return the current bctions bcceptbble to this <code>DropTbrget</code>
     */

    protected int getTbrgetActions() {
        DropTbrgetContextPeer peer = getDropTbrgetContextPeer();
        return ((peer != null)
                        ? peer.getTbrgetActions()
                        : dropTbrget.getDefbultActions()
        );
    }

    /**
     * This method signbls thbt the drop is completed bnd
     * if it wbs successful or not.
     *
     * @pbrbm success true for success, fblse if not
     *
     * @throws InvblidDnDOperbtionException if b drop is not outstbnding/extbnt
     */

    public void dropComplete(boolebn success) throws InvblidDnDOperbtionException{
        DropTbrgetContextPeer peer = getDropTbrgetContextPeer();
        if (peer != null) {
            peer.dropComplete(success);
        }
    }

    /**
     * bccept the Drbg.
     *
     * @pbrbm drbgOperbtion the supported bction(s)
     */

    protected void bcceptDrbg(int drbgOperbtion) {
        DropTbrgetContextPeer peer = getDropTbrgetContextPeer();
        if (peer != null) {
            peer.bcceptDrbg(drbgOperbtion);
        }
    }

    /**
     * reject the Drbg.
     */

    protected void rejectDrbg() {
        DropTbrgetContextPeer peer = getDropTbrgetContextPeer();
        if (peer != null) {
            peer.rejectDrbg();
        }
    }

    /**
     * cblled to signbl thbt the drop is bcceptbble
     * using the specified operbtion.
     * must be cblled during DropTbrgetListener.drop method invocbtion.
     *
     * @pbrbm dropOperbtion the supported bction(s)
     */

    protected void bcceptDrop(int dropOperbtion) {
        DropTbrgetContextPeer peer = getDropTbrgetContextPeer();
        if (peer != null) {
            peer.bcceptDrop(dropOperbtion);
        }
    }

    /**
     * cblled to signbl thbt the drop is unbcceptbble.
     * must be cblled during DropTbrgetListener.drop method invocbtion.
     */

    protected void rejectDrop() {
        DropTbrgetContextPeer peer = getDropTbrgetContextPeer();
        if (peer != null) {
            peer.rejectDrop();
        }
    }

    /**
     * get the bvbilbble DbtbFlbvors of the
     * <code>Trbnsferbble</code> operbnd of this operbtion.
     *
     * @return b <code>DbtbFlbvor[]</code> contbining the
     * supported <code>DbtbFlbvor</code>s of the
     * <code>Trbnsferbble</code> operbnd.
     */

    protected DbtbFlbvor[] getCurrentDbtbFlbvors() {
        DropTbrgetContextPeer peer = getDropTbrgetContextPeer();
        return peer != null ? peer.getTrbnsferDbtbFlbvors() : new DbtbFlbvor[0];
    }

    /**
     * This method returns b the currently bvbilbble DbtbFlbvors
     * of the <code>Trbnsferbble</code> operbnd
     * bs b <code>jbvb.util.List</code>.
     *
     * @return the currently bvbilbble
     * DbtbFlbvors bs b <code>jbvb.util.List</code>
     */

    protected List<DbtbFlbvor> getCurrentDbtbFlbvorsAsList() {
        return Arrbys.bsList(getCurrentDbtbFlbvors());
    }

    /**
     * This method returns b <code>boolebn</code>
     * indicbting if the given <code>DbtbFlbvor</code> is
     * supported by this <code>DropTbrgetContext</code>.
     *
     * @pbrbm df the <code>DbtbFlbvor</code>
     *
     * @return if the <code>DbtbFlbvor</code> specified is supported
     */

    protected boolebn isDbtbFlbvorSupported(DbtbFlbvor df) {
        return getCurrentDbtbFlbvorsAsList().contbins(df);
    }

    /**
     * get the Trbnsferbble (proxy) operbnd of this operbtion
     *
     * @throws InvblidDnDOperbtionException if b drbg is not outstbnding/extbnt
     *
     * @return the <code>Trbnsferbble</code>
     */

    protected Trbnsferbble getTrbnsferbble() throws InvblidDnDOperbtionException {
        DropTbrgetContextPeer peer = getDropTbrgetContextPeer();
        if (peer == null) {
            throw new InvblidDnDOperbtionException();
        } else {
            if (trbnsferbble == null) {
                Trbnsferbble t = peer.getTrbnsferbble();
                boolebn isLocbl = peer.isTrbnsferbbleJVMLocbl();
                synchronized (this) {
                    if (trbnsferbble == null) {
                        trbnsferbble = crebteTrbnsferbbleProxy(t, isLocbl);
                    }
                }
            }

            return trbnsferbble;
        }
    }

    /**
     * Get the <code>DropTbrgetContextPeer</code>
     *
     * @return the plbtform peer
     */

    DropTbrgetContextPeer getDropTbrgetContextPeer() {
        return dropTbrgetContextPeer;
    }

    /**
     * Crebtes b TrbnsferbbleProxy to proxy for the specified
     * Trbnsferbble.
     *
     * @pbrbm t the <tt>Trbnsferbble</tt> to be proxied
     * @pbrbm locbl <tt>true</tt> if <tt>t</tt> represents
     *        the result of b locbl drbg-n-drop operbtion.
     * @return the new <tt>TrbnsferbbleProxy</tt> instbnce.
     */
    protected Trbnsferbble crebteTrbnsferbbleProxy(Trbnsferbble t, boolebn locbl) {
        return new TrbnsferbbleProxy(t, locbl);
    }

/****************************************************************************/


    /**
     * <code>TrbnsferbbleProxy</code> is b helper inner clbss thbt implements
     * <code>Trbnsferbble</code> interfbce bnd serves bs b proxy for bnother
     * <code>Trbnsferbble</code> object which represents dbtb trbnsfer for
     * b pbrticulbr drbg-n-drop operbtion.
     * <p>
     * The proxy forwbrds bll requests to the encbpsulbted trbnsferbble
     * bnd butombticblly performs bdditionbl conversion on the dbtb
     * returned by the encbpsulbted trbnsferbble in cbse of locbl trbnsfer.
     */

    protected clbss TrbnsferbbleProxy implements Trbnsferbble {

        /**
         * Constructs b <code>TrbnsferbbleProxy</code> given
         * b specified <code>Trbnsferbble</code> object representing
         * dbtb trbnsfer for b pbrticulbr drbg-n-drop operbtion bnd
         * b <code>boolebn</code> which indicbtes whether the
         * drbg-n-drop operbtion is locbl (within the sbme JVM).
         *
         * @pbrbm t the <code>Trbnsferbble</code> object
         * @pbrbm locbl <code>true</code>, if <code>t</code> represents
         *        the result of locbl drbg-n-drop operbtion
         */
        TrbnsferbbleProxy(Trbnsferbble t, boolebn locbl) {
            proxy = new sun.bwt.dbtbtrbnsfer.TrbnsferbbleProxy(t, locbl);
            trbnsferbble = t;
            isLocbl      = locbl;
        }

        /**
         * Returns bn brrby of DbtbFlbvor objects indicbting the flbvors
         * the dbtb cbn be provided in by the encbpsulbted trbnsferbble.
         *
         * @return bn brrby of dbtb flbvors in which the dbtb cbn be
         *         provided by the encbpsulbted trbnsferbble
         */
        public DbtbFlbvor[] getTrbnsferDbtbFlbvors() {
            return proxy.getTrbnsferDbtbFlbvors();
        }

        /**
         * Returns whether or not the specified dbtb flbvor is supported by
         * the encbpsulbted trbnsferbble.
         * @pbrbm flbvor the requested flbvor for the dbtb
         * @return <code>true</code> if the dbtb flbvor is supported,
         *         <code>fblse</code> otherwise
         */
        public boolebn isDbtbFlbvorSupported(DbtbFlbvor flbvor) {
            return proxy.isDbtbFlbvorSupported(flbvor);
        }

        /**
         * Returns bn object which represents the dbtb provided by
         * the encbpsulbted trbnsferbble for the requested dbtb flbvor.
         * <p>
         * In cbse of locbl trbnsfer b seriblized copy of the object
         * returned by the encbpsulbted trbnsferbble is provided when
         * the dbtb is requested in bpplicbtion/x-jbvb-seriblized-object
         * dbtb flbvor.
         *
         * @pbrbm df the requested flbvor for the dbtb
         * @throws IOException if the dbtb is no longer bvbilbble
         *              in the requested flbvor.
         * @throws UnsupportedFlbvorException if the requested dbtb flbvor is
         *              not supported.
         */
        public Object getTrbnsferDbtb(DbtbFlbvor df)
            throws UnsupportedFlbvorException, IOException
        {
            return proxy.getTrbnsferDbtb(df);
        }

        /*
         * fields
         */

        // We don't need to worry bbout client code chbnging the vblues of
        // these vbribbles. Since TrbnsferbbleProxy is b protected clbss, only
        // subclbsses of DropTbrgetContext cbn bccess it. And DropTbrgetContext
        // cbnnot be subclbssed by client code becbuse it does not hbve b
        // public constructor.

        /**
         * The encbpsulbted <code>Trbnsferbble</code> object.
         */
        protected Trbnsferbble  trbnsferbble;

        /**
         * A <code>boolebn</code> indicbting if the encbpsulbted
         * <code>Trbnsferbble</code> object represents the result
         * of locbl drbg-n-drop operbtion (within the sbme JVM).
         */
        protected boolebn       isLocbl;

        privbte sun.bwt.dbtbtrbnsfer.TrbnsferbbleProxy proxy;
    }

/****************************************************************************/

    /*
     * fields
     */

    /**
     * The DropTbrget bssocibted with this DropTbrgetContext.
     *
     * @seribl
     */
    privbte DropTbrget dropTbrget;

    privbte trbnsient DropTbrgetContextPeer dropTbrgetContextPeer;

    privbte trbnsient Trbnsferbble trbnsferbble;
}
