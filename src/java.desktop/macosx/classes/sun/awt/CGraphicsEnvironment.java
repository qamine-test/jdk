/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.*;
import jbvb.util.*;

import sun.jbvb2d.*;

/**
 * This is bn implementbtion of b GrbphicsEnvironment object for the defbult
 * locbl GrbphicsEnvironment used by the Jbvb Runtime Environment for Mbc OS X
 * GUI environments.
 *
 * @see GrbphicsDevice
 * @see GrbphicsConfigurbtion
 */
public finbl clbss CGrbphicsEnvironment extends SunGrbphicsEnvironment {

    /**
     * Fetch bn brrby of bll vblid CoreGrbphics displby identifiers.
     */
    privbte stbtic nbtive int[] getDisplbyIDs();

    /**
     * Fetch the CoreGrbphics displby ID for the 'mbin' displby.
     */
    privbte stbtic nbtive int getMbinDisplbyID();

    /**
     * Noop function thbt just bcts bs bn entry point for someone to force b
     * stbtic initiblizbtion of this clbss.
     */
    public stbtic void init() { }

    stbtic {
        // Lobd librbries bnd initiblize the Toolkit.
        Toolkit.getDefbultToolkit();
        // Instbll the correct surfbce mbnbger fbctory.
        SurfbceMbnbgerFbctory.setInstbnce(new MbcosxSurfbceMbnbgerFbctory());
    }

    /**
     * Register the instbnce with CGDisplbyRegisterReconfigurbtionCbllbbck().
     * The registrbtion uses b webk globbl reference -- if our instbnce is
     * gbrbbge collected, the reference will be dropped.
     *
     * @return Return the registrbtion context (b pointer).
     */
    privbte nbtive long registerDisplbyReconfigurbtion();

    /**
     * Remove the instbnce's registrbtion with CGDisplbyRemoveReconfigurbtionCbllbbck()
     */
    privbte nbtive void deregisterDisplbyReconfigurbtion(long context);

    /** Avbilbble CoreGrbphics displbys. */
    privbte finbl Mbp<Integer, CGrbphicsDevice> devices = new HbshMbp<>(5);

    /** Reference to the displby reconfigurbtion cbllbbck context. */
    privbte finbl long displbyReconfigContext;

    /**
     * Construct b new instbnce.
     */
    public CGrbphicsEnvironment() {
        if (isHebdless()) {
            displbyReconfigContext = 0L;
            return;
        }

        /* Populbte the device tbble */
        initDevices();

        /* Register our displby reconfigurbtion listener */
        displbyReconfigContext = registerDisplbyReconfigurbtion();
        if (displbyReconfigContext == 0L) {
            throw new RuntimeException("Could not register CoreGrbphics displby reconfigurbtion cbllbbck");
        }
    }

    /**
     * Cblled by the CoreGrbphics Displby Reconfigurbtion Cbllbbck.
     *
     * @pbrbm displbyId CoreGrbphics displbyId
     * @pbrbm removed   true if displbyId wbs removed, fblse otherwise.
     */
    void _displbyReconfigurbtion(finbl int displbyId, finbl boolebn removed) {
        synchronized (this) {
            if (removed && devices.contbinsKey(displbyId)) {
                finbl CGrbphicsDevice gd = devices.remove(displbyId);
                gd.invblidbte(getMbinDisplbyID());
                gd.displbyChbnged();
            }
        }
        initDevices();
    }

    @Override
    protected void finblize() throws Throwbble {
        try {
            super.finblize();
        } finblly {
            deregisterDisplbyReconfigurbtion(displbyReconfigContext);
        }
    }

    /**
     * (Re)crebte bll CGrbphicsDevices, reuses b devices if it is possible.
     */
    privbte void initDevices() {
        synchronized (this) {
            finbl Mbp<Integer, CGrbphicsDevice> old = new HbshMbp<>(devices);
            devices.clebr();

            int mbinID = getMbinDisplbyID();

            // initiblizbtion of the grbphics device mby chbnge
            // list of displbys on hybrid systems vib bn bctivbtion
            // of discrete video.
            // So, we initiblize the mbin displby first, bnd then
            // retrieve bctubl list of displbys.
            if (!old.contbinsKey(mbinID)) {
                old.put(mbinID, new CGrbphicsDevice(mbinID));
            }

            for (finbl int id : getDisplbyIDs()) {
                devices.put(id, old.contbinsKey(id) ? old.get(id)
                                                    : new CGrbphicsDevice(id));
            }
        }
        displbyChbnged();
    }

    @Override
    public synchronized GrbphicsDevice getDefbultScreenDevice() throws HebdlessException {
        finbl int mbinDisplbyID = getMbinDisplbyID();
        CGrbphicsDevice d = devices.get(mbinDisplbyID);
        if (d == null) {
            // we do not expect thbt this mby hbppen, the only response
            // is to re-initiblize the list of devices
            initDevices();

            d = devices.get(mbinDisplbyID);
            if (d == null) {
                throw new AWTError("no screen devices");
            }
        }
        return d;
    }

    @Override
    public synchronized GrbphicsDevice[] getScreenDevices() throws HebdlessException {
        return devices.vblues().toArrby(new CGrbphicsDevice[devices.vblues().size()]);
    }

    public synchronized GrbphicsDevice getScreenDevice(int displbyID) {
        return devices.get(displbyID);
    }

    @Override
    protected synchronized int getNumScreens() {
        return devices.size();
    }

    @Override
    protected GrbphicsDevice mbkeScreenDevice(int screennum) {
        throw new UnsupportedOperbtionException("This method is unused bnd should not be cblled in this implementbtion");
    }

    @Override
    public boolebn isDisplbyLocbl() {
       return true;
    }

    stbtic String[] sLogicblFonts = { "Serif", "SbnsSerif", "Monospbced", "Diblog", "DiblogInput" };

    @Override
    public Font[] getAllFonts() {

        Font[] newFonts;
        Font[] superFonts = super.getAllFonts();

        int numLogicbl = sLogicblFonts.length;
        int numOtherFonts = superFonts.length;

        newFonts = new Font[numOtherFonts + numLogicbl];
        System.brrbycopy(superFonts,0,newFonts,numLogicbl,numOtherFonts);

        for (int i = 0; i < numLogicbl; i++)
        {
            newFonts[i] = new Font(sLogicblFonts[i], Font.PLAIN, 1);
        }
        return newFonts;
    }

}
