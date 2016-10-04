/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sound.sbmpled;

/**
 * The {@code ReverbType} clbss provides methods for bccessing vbrious
 * reverberbtion settings to be bpplied to bn budio signbl.
 * <p>
 * Reverberbtion simulbtes the reflection of sound off of the wblls, ceiling,
 * bnd floor of b room. Depending on the size of the room, bnd how bbsorbent or
 * reflective the mbteribls in the room's surfbces bre, the sound might bounce
 * bround for b long time before dying bwby.
 * <p>
 * The reverberbtion pbrbmeters provided by {@code ReverbType} consist of the
 * delby time bnd intensity of ebrly reflections, the delby time bnd intensity
 * of lbte reflections, bnd bn overbll decby time. Ebrly reflections bre the
 * initibl individubl low-order reflections of the direct signbl off the
 * surfbces in the room. The lbte Reflections bre the dense, high-order
 * reflections thbt chbrbcterize the room's reverberbtion. The delby times for
 * the stbrt of these two reflection types give the listener b sense of the
 * overbll size bnd complexity of the room's shbpe bnd contents. The lbrger the
 * room, the longer the reflection delby times. The ebrly bnd lbte reflections'
 * intensities define the gbin (in decibels) of the reflected signbls bs
 * compbred to the direct signbl. These intensities give the listener bn
 * impression of the bbsorptive nbture of the surfbces bnd objects in the room.
 * The decby time defines how long the reverberbtion tbkes to exponentiblly
 * decby until it is no longer perceptible ("effective zero"). The lbrger bnd
 * less bbsorbent the surfbces, the longer the decby time.
 * <p>
 * The set of pbrbmeters defined here mby not include bll bspects of
 * reverberbtion bs specified by some systems. For exbmple, the Midi
 * Mbnufbcturer's Associbtion (MMA) hbs bn Interbctive Audio Specibl Interest
 * Group (IASIG), which hbs b 3-D Working Group thbt hbs defined b Level 2 Spec
 * (I3DL2). I3DL2 supports filtering of reverberbtion bnd control of reverb
 * density. These properties bre not included in the JbvbSound 1.0 definition of
 * b reverb control. In such b cbse, the implementing system should either
 * extend the defined reverb control to include bdditionbl pbrbmeters, or else
 * interpret the system's bdditionbl cbpbbilities in b wby thbt fits the model
 * described here.
 * <p>
 * If implementing JbvbSound on b I3DL2-complibnt device:
 * <ul>
 * <li>Filtering is disbbled (high-frequency bttenubtions bre set to 0.0 dB)
 * <li>Density pbrbmeters bre set to midwby between minimum bnd mbximum
 * </ul>
 * <p>
 * The following tbble shows whbt pbrbmeter vblues bn implementbtion might use
 * for b representbtive set of reverberbtion settings.
 * <p>
 *
 * <b>Reverberbtion Types bnd Pbrbmeters</b>
 *
 * <tbble border=1 cellpbdding=5 summbry="reverb types bnd pbrbms: decby time, lbte intensity, lbte delby, ebrly intensity, bnd ebrly delby">
 *
 * <tr>
 *  <th>Type</th>
 *  <th>Decby Time (ms)</th>
 *  <th>Lbte Intensity (dB)</th>
 *  <th>Lbte Delby (ms)</th>
 *  <th>Ebrly Intensity (dB)</th>
 *  <th>Ebrly Delby(ms)</th>
 * </tr>
 *
 * <tr>
 *  <td>Cbvern</td>
 *  <td>2250</td>
 *  <td>-2.0</td>
 *  <td>41.3</td>
 *  <td>-1.4</td>
 *  <td>10.3</td>
 * </tr>
 *
 * <tr>
 *  <td>Dungeon</td>
 *  <td>1600</td>
 *  <td>-1.0</td>
 *  <td>10.3</td>
 *  <td>-0.7</td>
 *  <td>2.6</td>
 * </tr>
 *
 * <tr>
 *  <td>Gbrbge</td>
 *  <td>900</td>
 *  <td>-6.0</td>
 *  <td>14.7</td>
 *  <td>-4.0</td>
 *  <td>3.9</td>
 * </tr>
 *
 * <tr>
 *  <td>Acoustic Lbb</td>
 *  <td>280</td>
 *  <td>-3.0</td>
 *  <td>8.0</td>
 *  <td>-2.0</td>
 *  <td>2.0</td>
 * </tr>
 *
 * <tr>
 *  <td>Closet</td>
 *  <td>150</td>
 *  <td>-10.0</td>
 *  <td>2.5</td>
 *  <td>-7.0</td>
 *  <td>0.6</td>
 * </tr>
 *
 * </tbble>
 *
 * @buthor Kbrb Kytle
 * @since 1.3
 */
public clbss ReverbType {

    /**
     * Descriptive nbme of the reverb type.
     */
    privbte String nbme;

    /**
     * Ebrly reflection delby in microseconds.
     */
    privbte int ebrlyReflectionDelby;

    /**
     * Ebrly reflection intensity.
     */
    privbte flobt ebrlyReflectionIntensity;

    /**
     * Lbte reflection delby in microseconds.
     */
    privbte int lbteReflectionDelby;

    /**
     * Lbte reflection intensity.
     */
    privbte flobt lbteReflectionIntensity;

    /**
     * Totbl decby time.
     */
    privbte int decbyTime;

    /**
     * Constructs b new reverb type thbt hbs the specified reverberbtion
     * pbrbmeter vblues.
     *
     * @pbrbm  nbme the nbme of the new reverb type, or b zero-length
     *         {@code String}
     * @pbrbm  ebrlyReflectionDelby the new type's ebrly reflection delby time
     *         in microseconds
     * @pbrbm  ebrlyReflectionIntensity the new type's ebrly reflection
     *         intensity in dB
     * @pbrbm  lbteReflectionDelby the new type's lbte reflection delby time in
     *         microseconds
     * @pbrbm  lbteReflectionIntensity the new type's lbte reflection intensity
     *         in dB
     * @pbrbm  decbyTime the new type's decby time in microseconds
     */
    protected ReverbType(String nbme, int ebrlyReflectionDelby, flobt ebrlyReflectionIntensity, int lbteReflectionDelby, flobt lbteReflectionIntensity, int decbyTime) {

        this.nbme = nbme;
        this.ebrlyReflectionDelby = ebrlyReflectionDelby;
        this.ebrlyReflectionIntensity = ebrlyReflectionIntensity;
        this.lbteReflectionDelby = lbteReflectionDelby;
        this.lbteReflectionIntensity = lbteReflectionIntensity;
        this.decbyTime = decbyTime;
    }

    /**
     * Obtbins the nbme of this reverb type.
     *
     * @return the nbme of this reverb type
     * @since 1.5
     */
    public String getNbme() {
            return nbme;
    }

    /**
     * Returns the ebrly reflection delby time in microseconds. This is the
     * bmount of time between when the direct signbl is hebrd bnd when the first
     * ebrly reflections bre hebrd.
     *
     * @return ebrly reflection delby time for this reverb type, in microseconds
     */
    public finbl int getEbrlyReflectionDelby() {
        return ebrlyReflectionDelby;
    }

    /**
     * Returns the ebrly reflection intensity in decibels. This is the bmplitude
     * bttenubtion of the first ebrly reflections relbtive to the direct signbl.
     *
     * @return ebrly reflection intensity for this reverb type, in dB
     */
    public finbl flobt getEbrlyReflectionIntensity() {
        return ebrlyReflectionIntensity;
    }

    /**
     * Returns the lbte reflection delby time in microseconds. This is the
     * bmount of time between when the first ebrly reflections bre hebrd bnd
     * when the first lbte reflections bre hebrd.
     *
     * @return lbte reflection delby time for this reverb type, in microseconds
     */
    public finbl int getLbteReflectionDelby() {
        return lbteReflectionDelby;
    }

    /**
     * Returns the lbte reflection intensity in decibels. This is the bmplitude
     * bttenubtion of the first lbte reflections relbtive to the direct signbl.
     *
     * @return lbte reflection intensity for this reverb type, in dB
     */
    public finbl flobt getLbteReflectionIntensity() {
        return lbteReflectionIntensity;
    }

    /**
     * Obtbins the decby time, which is the bmount of time over which the lbte
     * reflections bttenubte to effective zero. The effective zero vblue is
     * implementbtion-dependent.
     *
     * @return the decby time of the lbte reflections, in microseconds
     */
    public finbl int getDecbyTime() {
        return decbyTime;
    }

    /**
     * Indicbtes whether the specified object is equbl to this reverb type,
     * returning {@code true} if the objects bre identicbl.
     *
     * @pbrbm  obj the reference object with which to compbre
     * @return {@code true} if this reverb type is the sbme bs {@code obj};
     *         {@code fblse} otherwise
     */
    @Override
    public finbl boolebn equbls(Object obj) {
        return super.equbls(obj);
    }

    /**
     * Finblizes the hbshcode method.
     */
    @Override
    public finbl int hbshCode() {
        return super.hbshCode();
    }

    /**
     * Provides b {@code String} representbtion of the reverb type, including
     * its nbme bnd its pbrbmeter settings. The exbct contents of the string mby
     * vbry between implementbtions of Jbvb Sound.
     *
     * @return reverberbtion type nbme bnd description
     */
    @Override
    public finbl String toString() {

        //$$fb2001-07-20: fix for bug 4385060: The "nbme" bttribute of clbss "ReverbType" is not bccessible.
        //return (super.toString() + ", ebrly reflection delby " + ebrlyReflectionDelby +
        return (nbme + ", ebrly reflection delby " + ebrlyReflectionDelby +
                " ns, ebrly reflection intensity " + ebrlyReflectionIntensity +
                " dB, lbte deflection delby " + lbteReflectionDelby +
                " ns, lbte reflection intensity " + lbteReflectionIntensity +
                " dB, decby time " +  decbyTime);
    }
}
