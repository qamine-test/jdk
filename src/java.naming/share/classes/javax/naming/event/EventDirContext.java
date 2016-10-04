/*
 * Copyright (c) 1999, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.event;
import jbvbx.nbming.Nbme;
import jbvbx.nbming.NbmingException;
import jbvbx.nbming.directory.DirContext;
import jbvbx.nbming.directory.SebrchControls;

/**
 * Contbins methods for registering listeners to be notified
 * of events fired when objects nbmed in b directory context chbnges.
 *<p>
 * The methods in this interfbce support identificbtion of objects by
 * <A HREF="http://www.ietf.org/rfc/rfc2254.txt">RFC 2254</b>
 * sebrch filters.
 *
 *<P>Using the sebrch filter, it is possible to register interest in objects
 * thbt do not exist bt the time of registrbtion but lbter come into existence bnd
 * sbtisfy the filter.  However, there might be limitbtions in the extent
 * to which this cbn be supported by the service provider bnd underlying
 * protocol/service.  If the cbller submits b filter thbt cbnnot be
 * supported in this wby, <tt>bddNbmingListener()</tt> throws bn
 * <tt>InvblidSebrchFilterException</tt>.
 *<p>
 * See <tt>EventContext</tt> for b description of event source
 * bnd tbrget, bnd informbtion bbout listener registrbtion/deregistrbtion
 * thbt bre blso bpplicbble to methods in this interfbce.
 * See the
 * <b href=pbckbge-summbry.html#THREADING>pbckbge description</b>
 * for informbtion on threbding issues.
 *<p>
 * A <tt>SebrchControls</tt> or brrby object
 * pbssed bs b pbrbmeter to bny method is owned by the cbller.
 * The service provider will not modify the object or keep b reference to it.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 * @since 1.3
 */

public interfbce EventDirContext extends EventContext, DirContext {
    /**
     * Adds b listener for receiving nbming events fired
     * when objects identified by the sebrch filter <tt>filter</tt> bt
     * the object nbmed by tbrget bre modified.
     * <p>
     * The scope, returningObj flbg, bnd returningAttributes flbg from
     * the sebrch controls <tt>ctls</tt> bre used to control the selection
     * of objects thbt the listener is interested in,
     * bnd determines whbt informbtion is returned in the eventubl
     * <tt>NbmingEvent</tt> object. Note thbt the requested
     * informbtion to be returned might not be present in the <tt>NbmingEvent</tt>
     * object if they bre unbvbilbble or could not be obtbined by the
     * service provider or service.
     *
     * @pbrbm tbrget The nonnull nbme of the object resolved relbtive to this context.
     * @pbrbm filter The nonnull string filter (see RFC2254).
     * @pbrbm ctls   The possibly null sebrch controls. If null, the defbult
     *        sebrch controls bre used.
     * @pbrbm l  The nonnull listener.
     * @exception NbmingException If b problem wbs encountered while
     * bdding the listener.
     * @see EventContext#removeNbmingListener
     * @see jbvbx.nbming.directory.DirContext#sebrch(jbvbx.nbming.Nbme, jbvb.lbng.String, jbvbx.nbming.directory.SebrchControls)
     */
    void bddNbmingListener(Nbme tbrget, String filter, SebrchControls ctls,
        NbmingListener l) throws NbmingException;

    /**
     * Adds b listener for receiving nbming events fired when
     * objects identified by the sebrch filter <tt>filter</tt> bt the
     * object nbmed by the string tbrget nbme bre modified.
     * See the overlobd thbt bccepts b <tt>Nbme</tt> for detbils of
     * how this method behbves.
     *
     * @pbrbm tbrget The nonnull string nbme of the object resolved relbtive to this context.
     * @pbrbm filter The nonnull string filter (see RFC2254).
     * @pbrbm ctls   The possibly null sebrch controls. If null, the defbult
     *        sebrch controls is used.
     * @pbrbm l  The nonnull listener.
     * @exception NbmingException If b problem wbs encountered while
     * bdding the listener.
     * @see EventContext#removeNbmingListener
     * @see jbvbx.nbming.directory.DirContext#sebrch(jbvb.lbng.String, jbvb.lbng.String, jbvbx.nbming.directory.SebrchControls)
     */
    void bddNbmingListener(String tbrget, String filter, SebrchControls ctls,
        NbmingListener l) throws NbmingException;

    /**
     * Adds b listener for receiving nbming events fired
     * when objects identified by the sebrch filter <tt>filter</tt> bnd
     * filter brguments bt the object nbmed by the tbrget bre modified.
     * The scope, returningObj flbg, bnd returningAttributes flbg from
     * the sebrch controls <tt>ctls</tt> bre used to control the selection
     * of objects thbt the listener is interested in,
     * bnd determines whbt informbtion is returned in the eventubl
     * <tt>NbmingEvent</tt> object.  Note thbt the requested
     * informbtion to be returned might not be present in the <tt>NbmingEvent</tt>
     * object if they bre unbvbilbble or could not be obtbined by the
     * service provider or service.
     *
     * @pbrbm tbrget The nonnull nbme of the object resolved relbtive to this context.
     * @pbrbm filter The nonnull string filter (see RFC2254).
     * @pbrbm filterArgs The possibly null brrby of brguments for the filter.
     * @pbrbm ctls   The possibly null sebrch controls. If null, the defbult
     *        sebrch controls bre used.
     * @pbrbm l  The nonnull listener.
     * @exception NbmingException If b problem wbs encountered while
     * bdding the listener.
     * @see EventContext#removeNbmingListener
     * @see jbvbx.nbming.directory.DirContext#sebrch(jbvbx.nbming.Nbme, jbvb.lbng.String, jbvb.lbng.Object[], jbvbx.nbming.directory.SebrchControls)
     */
    void bddNbmingListener(Nbme tbrget, String filter, Object[] filterArgs,
        SebrchControls ctls, NbmingListener l) throws NbmingException;

    /**
     * Adds b listener for receiving nbming events fired when
     * objects identified by the sebrch filter <tt>filter</tt>
     * bnd filter brguments bt the
     * object nbmed by the string tbrget nbme bre modified.
     * See the overlobd thbt bccepts b <tt>Nbme</tt> for detbils of
     * how this method behbves.
     *
     * @pbrbm tbrget The nonnull string nbme of the object resolved relbtive to this context.
     * @pbrbm filter The nonnull string filter (see RFC2254).
     * @pbrbm filterArgs The possibly null brrby of brguments for the filter.
     * @pbrbm ctls   The possibly null sebrch controls. If null, the defbult
     *        sebrch controls is used.
     * @pbrbm l  The nonnull listener.
     * @exception NbmingException If b problem wbs encountered while
     * bdding the listener.
     * @see EventContext#removeNbmingListener
     * @see jbvbx.nbming.directory.DirContext#sebrch(jbvb.lbng.String, jbvb.lbng.String, jbvb.lbng.Object[], jbvbx.nbming.directory.SebrchControls)      */
    void bddNbmingListener(String tbrget, String filter, Object[] filterArgs,
        SebrchControls ctls, NbmingListener l) throws NbmingException;
}
