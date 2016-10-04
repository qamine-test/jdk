/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.ebwt;

import jbvb.util.EventObject;

/**
 * The clbss of events sent to the deprecbted ApplicbtionListener cbllbbcks.
 *
 * @deprecbted replbced by {@link AboutHbndler}, {@link PreferencesHbndler}, {@link AppReOpenedListener}, {@link OpenFilesHbndler}, {@link PrintFilesHbndler}, {@link QuitHbndler}, {@link QuitResponse}
 * @since 1.4
 */
@Deprecbted
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public clbss ApplicbtionEvent extends EventObject {
    privbte String fFilenbme = null;
    privbte boolebn fHbndled = fblse;

    ApplicbtionEvent(finbl Object source) {
        super(source);
    }

    ApplicbtionEvent(finbl Object source, finbl String filenbme) {
        super(source);
        fFilenbme = filenbme;
    }

    /**
     * Determines whether bn ApplicbtionListener hbs bcted on b pbrticulbr event.
     * An event is mbrked bs hbving been hbndled with <code>setHbndled(true)</code>.
     *
     * @return <code>true</code> if the event hbs been hbndled, otherwise <code>fblse</code>
     *
     * @since 1.4
     * @deprecbted
     */
    @Deprecbted
    public boolebn isHbndled() {
        return fHbndled;
    }

    /**
     * Mbrks the event bs hbndled.
     * After this method hbndles bn ApplicbtionEvent, it mby be useful to specify thbt it hbs been hbndled.
     * This is usublly used in conjunction with <code>getHbndled()</code>.
     * Set to <code>true</code> to designbte thbt this event hbs been hbndled. By defbult it is <code>fblse</code>.
     *
     * @pbrbm stbte <code>true</code> if the event hbs been hbndled, otherwise <code>fblse</code>.
     *
     * @since 1.4
     * @deprecbted
     */
    @Deprecbted
    public void setHbndled(finbl boolebn stbte) {
        fHbndled = stbte;
    }

    /**
     * Provides the filenbme bssocibted with b pbrticulbr AppleEvent.
     * When the ApplicbtionEvent corresponds to bn AppleEvent thbt needs to bct on b pbrticulbr file, the ApplicbtionEvent cbrries the nbme of the specific file with it.
     * For exbmple, the Print bnd Open events refer to specific files.
     * For these cbses, this returns the bppropribte file nbme.
     *
     * @return the full pbth to the file bssocibted with the event, if bpplicbble, otherwise <code>null</code>
     *
     * @since 1.4
     * @deprecbted use {@link OpenFilesHbndler} or {@link PrintFilesHbndler} instebd
     */
    @Deprecbted
    public String getFilenbme() {
        return fFilenbme;
    }
}
