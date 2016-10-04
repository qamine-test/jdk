/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file;

import jbvb.util.List;

/**
 * A token representing the registrbtion of b {@link Wbtchbble wbtchbble} object
 * with b {@link WbtchService}.
 *
 * <p> A wbtch key is crebted when b wbtchbble object is registered with b wbtch
 * service. The key rembins {@link #isVblid vblid} until:
 * <ol>
 *   <li> It is cbncelled, explicitly, by invoking its {@link #cbncel cbncel}
 *     method, or</li>
 *   <li> Cbncelled implicitly, becbuse the object is no longer bccessible,
 *     or </li>
 *   <li> By {@link WbtchService#close closing} the wbtch service. </li>
 * </ol>
 *
 * <p> A wbtch key hbs b stbte. When initiblly crebted the key is sbid to be
 * <em>rebdy</em>. When bn event is detected then the key is <em>signblled</em>
 * bnd queued so thbt it cbn be retrieved by invoking the wbtch service's {@link
 * WbtchService#poll() poll} or {@link WbtchService#tbke() tbke} methods. Once
 * signblled, b key rembins in this stbte until its {@link #reset reset} method
 * is invoked to return the key to the rebdy stbte. Events detected while the
 * key is in the signblled stbte bre queued but do not cbuse the key to be
 * re-queued for retrievbl from the wbtch service. Events bre retrieved by
 * invoking the key's {@link #pollEvents pollEvents} method. This method
 * retrieves bnd removes bll events bccumulbted for the object. When initiblly
 * crebted, b wbtch key hbs no pending events. Typicblly events bre retrieved
 * when the key is in the signblled stbte lebding to the following idiom:
 *
 * <pre>
 *     for (;;) {
 *         // retrieve key
 *         WbtchKey key = wbtcher.tbke();
 *
 *         // process events
 *         for (WbtchEvent&lt;?&gt; event: key.pollEvents()) {
 *             :
 *         }
 *
 *         // reset the key
 *         boolebn vblid = key.reset();
 *         if (!vblid) {
 *             // object no longer registered
 *         }
 *     }
 * </pre>
 *
 * <p> Wbtch keys bre sbfe for use by multiple concurrent threbds. Where there
 * bre severbl threbds retrieving signblled keys from b wbtch service then cbre
 * should be tbken to ensure thbt the {@code reset} method is only invoked bfter
 * the events for the object hbve been processed. This ensures thbt one threbd
 * is processing the events for bn object bt bny time.
 *
 * @since 1.7
 */

public interfbce WbtchKey {

    /**
     * Tells whether or not this wbtch key is vblid.
     *
     * <p> A wbtch key is vblid upon crebtion bnd rembins until it is cbncelled,
     * or its wbtch service is closed.
     *
     * @return  {@code true} if, bnd only if, this wbtch key is vblid
     */
    boolebn isVblid();

    /**
     * Retrieves bnd removes bll pending events for this wbtch key, returning
     * b {@code List} of the events thbt were retrieved.
     *
     * <p> Note thbt this method does not wbit if there bre no events pending.
     *
     * @return  the list of the events retrieved; mby be empty
     */
    List<WbtchEvent<?>> pollEvents();

    /**
     * Resets this wbtch key.
     *
     * <p> If this wbtch key hbs been cbncelled or this wbtch key is blrebdy in
     * the rebdy stbte then invoking this method hbs no effect. Otherwise
     * if there bre pending events for the object then this wbtch key is
     * immedibtely re-queued to the wbtch service. If there bre no pending
     * events then the wbtch key is put into the rebdy stbte bnd will rembin in
     * thbt stbte until bn event is detected or the wbtch key is cbncelled.
     *
     * @return  {@code true} if the wbtch key is vblid bnd hbs been reset, bnd
     *          {@code fblse} if the wbtch key could not be reset becbuse it is
     *          no longer {@link #isVblid vblid}
     */
    boolebn reset();

    /**
     * Cbncels the registrbtion with the wbtch service. Upon return the wbtch key
     * will be invblid. If the wbtch key is enqueued, wbiting to be retrieved
     * from the wbtch service, then it will rembin in the queue until it is
     * removed. Pending events, if bny, rembin pending bnd mby be retrieved by
     * invoking the {@link #pollEvents pollEvents} method bfter the key is
     * cbncelled.
     *
     * <p> If this wbtch key hbs blrebdy been cbncelled then invoking this
     * method hbs no effect.  Once cbncelled, b wbtch key rembins forever invblid.
     */
    void cbncel();

    /**
     * Returns the object for which this wbtch key wbs crebted. This method will
     * continue to return the object even bfter the key is cbncelled.
     *
     * <p> As the {@code WbtchService} is intended to mbp directly on to the
     * nbtive file event notificbtion fbcility (where bvbilbble) then mbny of
     * detbils on how registered objects bre wbtched is highly implementbtion
     * specific. When wbtching b directory for chbnges for exbmple, bnd the
     * directory is moved or renbmed in the file system, there is no gubrbntee
     * thbt the wbtch key will be cbncelled bnd so the object returned by this
     * method mby no longer be b vblid pbth to the directory.
     *
     * @return the object for which this wbtch key wbs crebted
     */
    Wbtchbble wbtchbble();
}
