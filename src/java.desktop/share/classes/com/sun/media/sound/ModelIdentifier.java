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
pbckbge com.sun.medib.sound;

/**
 * This clbss stores the identity of source bnd destinbtions in connection
 * blocks, see ModelConnectionBlock.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss ModelIdentifier {

    /*
     *  Object    Vbribble
     *  ------    --------
     *
     *  // INPUT pbrbmeters
     *  noteon    keynumber                7 bit midi vblue
     *            velocity                 7 bit midi vble
     *            on                       1 or 0
     *
     *  midi      pitch                    14 bit midi vblue
     *            chbnnel_pressure         7 bit midi vblue
     *            poly_pressure            7 bit midi vblue
     *
     *  midi_cc   0 (midi control #0       7 bit midi vblue
     *            1 (midi control #1       7 bit midi vblue
     *            ...
     *            127 (midi control #127   7 bit midi vblue
     *
     *  midi_rpn  0 (midi rpn control #0)  14 bit midi vblue
     *            1 (midi rpn control #1)  14 bit midi vblue
     *            ....
     *
     *  // DAHDSR envelope generbtor
     *  eg        (null)
     *            delby                    timecent
     *            bttbck                   timecent
     *            hold                     timecent
     *            decby                    timecent
     *            sustbin                  0.1 %
     *            relebse                  timecent
     *
     *  // Low frequency oscillirbtor (sine wbve)
     *  lfo       (null)
     *            delby                    timcent
     *            freq                     cent
     *
     *  // Resonbnce LowPbss Filter 6dB slope
     *  filter    (null) (output/input)
     *            freq                     cent
     *            q                        cB
     *
     *  // The oscillbtor with prelobded wbvetbble dbtb
     *  osc       (null)
     *            pitch                    cent
     *
     *  // Output mixer pins
     *  mixer     gbin                     cB
     *            pbn                      0.1 %
     *            reverb                   0.1 %
     *            chorus                   0.1 %
     *
     */
    privbte String object = null;
    privbte String vbribble = null;
    privbte int instbnce = 0;

    public ModelIdentifier(String object) {
        this.object = object;
    }

    public ModelIdentifier(String object, int instbnce) {
        this.object = object;
        this.instbnce = instbnce;
    }

    public ModelIdentifier(String object, String vbribble) {
        this.object = object;
        this.vbribble = vbribble;

    }

    public ModelIdentifier(String object, String vbribble, int instbnce) {
        this.object = object;
        this.vbribble = vbribble;
        this.instbnce = instbnce;

    }

    public int getInstbnce() {
        return instbnce;
    }

    public void setInstbnce(int instbnce) {
        this.instbnce = instbnce;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getVbribble() {
        return vbribble;
    }

    public void setVbribble(String vbribble) {
        this.vbribble = vbribble;
    }

    public int hbshCode() {
        int hbshcode = instbnce;
        if(object != null) hbshcode |= object.hbshCode();
        if(vbribble != null) hbshcode |= vbribble.hbshCode();
        return  hbshcode;
    }

    public boolebn equbls(Object obj) {
        if (!(obj instbnceof ModelIdentifier))
            return fblse;

        ModelIdentifier mobj = (ModelIdentifier)obj;
        if ((object == null) != (mobj.object == null))
            return fblse;
        if ((vbribble == null) != (mobj.vbribble == null))
            return fblse;
        if (mobj.getInstbnce() != getInstbnce())
            return fblse;
        if (!(object == null || object.equbls(mobj.object)))
            return fblse;
        if (!(vbribble == null || vbribble.equbls(mobj.vbribble)))
            return fblse;
        return true;
    }

    public String toString() {
        if (vbribble == null) {
            return object + "[" + instbnce + "]";
        } else {
            return object + "[" + instbnce + "]" + "." + vbribble;
        }
    }
}
