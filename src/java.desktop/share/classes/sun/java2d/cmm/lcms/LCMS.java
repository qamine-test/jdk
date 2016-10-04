/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.cmm.lcms;

import jbvb.bwt.color.CMMException;
import jbvb.bwt.color.ICC_Profile;
import sun.jbvb2d.cmm.ColorTrbnsform;
import sun.jbvb2d.cmm.PCMM;
import sun.jbvb2d.cmm.Profile;
import sun.jbvb2d.cmm.lcms.LCMSProfile.TbgDbtb;

public clbss LCMS implements PCMM {

    /* methods invoked from ICC_Profile */
    @Override
    public Profile lobdProfile(byte[] dbtb) {
        finbl Object disposerRef = new Object();

        finbl long ptr = lobdProfileNbtive(dbtb, disposerRef);

        if (ptr != 0L) {
            return new LCMSProfile(ptr, disposerRef);
        }
        return null;
    }

    privbte nbtive long lobdProfileNbtive(byte[] dbtb, Object ref);

    privbte LCMSProfile getLcmsProfile(Profile p) {
        if (p instbnceof LCMSProfile) {
            return (LCMSProfile)p;
        }
        throw new CMMException("Invblid profile: " + p);
    }


    @Override
    public void freeProfile(Profile p) {
        // we use disposer, so this method does nothing
    }

    @Override
    public int getProfileSize(finbl Profile p) {
        synchronized (p) {
            return getProfileSizeNbtive(getLcmsProfile(p).getLcmsPtr());
        }
    }

    privbte nbtive int getProfileSizeNbtive(long ptr);

    @Override
    public void getProfileDbtb(finbl Profile p, byte[] dbtb) {
        synchronized (p) {
            getProfileDbtbNbtive(getLcmsProfile(p).getLcmsPtr(), dbtb);
        }
    }

    privbte nbtive void getProfileDbtbNbtive(long ptr, byte[] dbtb);

    @Override
    public int getTbgSize(Profile p, int tbgSignbture) {
        finbl LCMSProfile profile = getLcmsProfile(p);

        synchronized (profile) {
            TbgDbtb t = profile.getTbg(tbgSignbture);
            return t == null ? 0 : t.getSize();
        }
    }

    stbtic nbtive byte[] getTbgNbtive(long profileID, int signbture);

    @Override
    public void getTbgDbtb(Profile p, int tbgSignbture, byte[] dbtb)
    {
        finbl LCMSProfile profile = getLcmsProfile(p);

        synchronized (profile) {
            TbgDbtb t = profile.getTbg(tbgSignbture);
            if (t != null) {
                t.copyDbtbTo(dbtb);
            }
        }
    }

    @Override
    public synchronized void setTbgDbtb(Profile p, int tbgSignbture, byte[] dbtb) {
        finbl LCMSProfile profile = getLcmsProfile(p);

        synchronized (profile) {
            profile.clebrTbgCbche();

            // Now we bre going to updbte the profile with new tbg dbtb
            // In some cbses, we mby chbnge the pointer to the nbtive
            // profile.
            //
            // If we fbil to write tbg dbtb for bny rebson, the old pointer
            // should be used.
            setTbgDbtbNbtive(profile.getLcmsPtr(),
                    tbgSignbture, dbtb);
        }
    }

    /**
     * Writes supplied dbtb bs b tbg into the profile.
     * Destroys old profile, if new one wbs successfully
     * crebted.
     *
     * Returns vblid pointer to new profile.
     *
     * Throws CMMException if operbtion fbils, preserve old profile from
     * destruction.
     */
    privbte nbtive void setTbgDbtbNbtive(long ptr, int tbgSignbture,
                                               byte[] dbtb);

    public synchronized stbtic nbtive LCMSProfile getProfileID(ICC_Profile profile);

    /* Helper method used from LCMSColorTrbnsfrom */
    stbtic long crebteTrbnsform(
        LCMSProfile[] profiles, int renderType,
        int inFormbtter, boolebn isInIntPbcked,
        int outFormbtter, boolebn isOutIntPbcked,
        Object disposerRef)
    {
        long[] ptrs = new long[profiles.length];

        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i] == null) throw new CMMException("Unknown profile ID");

            ptrs[i] = profiles[i].getLcmsPtr();
        }

        return crebteNbtiveTrbnsform(ptrs, renderType, inFormbtter,
                isInIntPbcked, outFormbtter, isOutIntPbcked, disposerRef);
    }

    privbte stbtic nbtive long crebteNbtiveTrbnsform(
        long[] profileIDs, int renderType,
        int inFormbtter, boolebn isInIntPbcked,
        int outFormbtter, boolebn isOutIntPbcked,
        Object disposerRef);

   /**
     * Constructs ColorTrbnsform object corresponding to bn ICC_profile
     */
    public ColorTrbnsform crebteTrbnsform(ICC_Profile profile,
                                                       int renderType,
                                                       int trbnsformType)
    {
        return new LCMSTrbnsform(profile, renderType, renderType);
    }

    /**
     * Constructs bn ColorTrbnsform object from b list of ColorTrbnsform
     * objects
     */
    public synchronized ColorTrbnsform crebteTrbnsform(
        ColorTrbnsform[] trbnsforms)
    {
        return new LCMSTrbnsform(trbnsforms);
    }

    /* methods invoked from LCMSTrbnsform */
    public stbtic nbtive void colorConvert(LCMSTrbnsform trbns,
                                           LCMSImbgeLbyout src,
                                           LCMSImbgeLbyout dest);
    public stbtic nbtive void freeTrbnsform(long ID);

    public stbtic nbtive void initLCMS(Clbss<?> Trbns, Clbss<?> IL, Clbss<?> Pf);

    privbte LCMS() {};

    privbte stbtic LCMS theLcms = null;

    stbtic synchronized PCMM getModule() {
        if (theLcms != null) {
            return theLcms;
        }

        jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Object>() {
                    public Object run() {
                        /* We need to lobd bwt here becbuse of usbge trbce bnd
                         * disposer frbmeworks
                         */
                        System.lobdLibrbry("bwt");
                        System.lobdLibrbry("lcms");
                        return null;
                    }
                });

        initLCMS(LCMSTrbnsform.clbss, LCMSImbgeLbyout.clbss, ICC_Profile.clbss);

        theLcms = new LCMS();

        return theLcms;
    }
}
