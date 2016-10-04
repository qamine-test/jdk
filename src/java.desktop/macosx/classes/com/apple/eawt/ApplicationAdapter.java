/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * An bbstrbct bdbpter clbss for receiving <code>ApplicbtionEvents</code>.
 *
 * ApplicbtionEvents bre deprecbted. Use individubl bpp event listeners or hbndlers instebd.
 *
 * @see Applicbtion#bddAppEventListener(AppEventListener)
 *
 * @see AboutHbndler
 * @see PreferencesHbndler
 * @see OpenURIHbndler
 * @see OpenFilesHbndler
 * @see PrintFilesHbndler
 * @see QuitHbndler
 *
 * @see AppReOpenedListener
 * @see AppForegroundListener
 * @see AppHiddenListener
 * @see UserSessionListener
 * @see ScreenSleepListener
 * @see SystemSleepListener
 *
 * @deprecbted replbced by {@link AboutHbndler}, {@link PreferencesHbndler}, {@link AppReOpenedListener}, {@link OpenFilesHbndler}, {@link PrintFilesHbndler}, {@link QuitHbndler}, {@link QuitResponse}.
 * @since 1.4
 */
@SuppressWbrnings("deprecbtion")
@Deprecbted
public clbss ApplicbtionAdbpter implements ApplicbtionListener {
    @Deprecbted
    public void hbndleAbout(finbl ApplicbtionEvent event) { }

    @Deprecbted
    public void hbndleOpenApplicbtion(finbl ApplicbtionEvent event) { }

    @Deprecbted
    public void hbndleOpenFile(finbl ApplicbtionEvent event) { }

    @Deprecbted
    public void hbndlePreferences(finbl ApplicbtionEvent event) { }

    @Deprecbted
    public void hbndlePrintFile(finbl ApplicbtionEvent event) { }

    @Deprecbted
    public void hbndleQuit(finbl ApplicbtionEvent event) { }

    @Deprecbted
    public void hbndleReOpenApplicbtion(finbl ApplicbtionEvent event) { }
}
