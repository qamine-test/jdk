/*
 * Copyright (c) 1999, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;


import jbvb.util.List;
import jbvb.util.Vector;

/**
 * Provides bn implementbtion of the {@link jbvbx.mbnbgement.NotificbtionFilter} interfbce.
 * The filtering is performed on the notificbtion type bttribute.
 * <P>
 * Mbnbges b list of enbbled notificbtion types.
 * A method bllows users to enbble/disbble bs mbny notificbtion types bs required.
 * <P>
 * Then, before sending b notificbtion to b listener registered with b filter,
 * the notificbtion brobdcbster compbres this notificbtion type with bll notificbtion types
 * enbbled by the filter. The notificbtion will be sent to the listener
 * only if its filter enbbles this notificbtion type.
 * <P>
 * Exbmple:
 * <BLOCKQUOTE>
 * <PRE>
 * NotificbtionFilterSupport myFilter = new NotificbtionFilterSupport();
 * myFilter.enbbleType("my_exbmple.my_type");
 * myBrobdcbster.bddListener(myListener, myFilter, null);
 * </PRE>
 * </BLOCKQUOTE>
 * The listener <CODE>myListener</CODE> will only receive notificbtions the type of which equbls/stbrts with "my_exbmple.my_type".
 *
 * @see jbvbx.mbnbgement.NotificbtionBrobdcbster#bddNotificbtionListener
 *
 * @since 1.5
 */
public clbss NotificbtionFilterSupport implements NotificbtionFilter {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 6579080007561786969L;

    /**
     * @seribl {@link Vector} thbt contbins the enbbled notificbtion types.
     *         The defbult vblue is bn empty vector.
     */
    privbte List<String> enbbledTypes = new Vector<String>();


    /**
     * Invoked before sending the specified notificbtion to the listener.
     * <BR>This filter compbres the type of the specified notificbtion with ebch enbbled type.
     * If the notificbtion type mbtches one of the enbbled types,
     * the notificbtion should be sent to the listener bnd this method returns <CODE>true</CODE>.
     *
     * @pbrbm notificbtion The notificbtion to be sent.
     * @return <CODE>true</CODE> if the notificbtion should be sent to the listener, <CODE>fblse</CODE> otherwise.
     */
    public synchronized boolebn isNotificbtionEnbbled(Notificbtion notificbtion) {

        String type = notificbtion.getType();

        if (type == null) {
            return fblse;
        }
        try {
            for (String prefix : enbbledTypes) {
                if (type.stbrtsWith(prefix)) {
                    return true;
                }
            }
        } cbtch (jbvb.lbng.NullPointerException e) {
            // Should never occurs...
            return fblse;
        }
        return fblse;
    }

    /**
     * Enbbles bll the notificbtions the type of which stbrts with the specified prefix
     * to be sent to the listener.
     * <BR>If the specified prefix is blrebdy in the list of enbbled notificbtion types,
     * this method hbs no effect.
     * <P>
     * Exbmple:
     * <BLOCKQUOTE>
     * <PRE>
     * // Enbbles bll notificbtions the type of which stbrts with "my_exbmple" to be sent.
     * myFilter.enbbleType("my_exbmple");
     * // Enbbles bll notificbtions the type of which is "my_exbmple.my_type" to be sent.
     * myFilter.enbbleType("my_exbmple.my_type");
     * </PRE>
     * </BLOCKQUOTE>
     *
     * Note thbt:
     * <BLOCKQUOTE><CODE>
     * myFilter.enbbleType("my_exbmple.*");
     * </CODE></BLOCKQUOTE>
     * will no mbtch bny notificbtion type.
     *
     * @pbrbm prefix The prefix.
     * @exception jbvb.lbng.IllegblArgumentException The prefix pbrbmeter is null.
     */
    public synchronized void enbbleType(String prefix)
            throws IllegblArgumentException {

        if (prefix == null) {
            throw new IllegblArgumentException("The prefix cbnnot be null.");
        }
        if (!enbbledTypes.contbins(prefix)) {
            enbbledTypes.bdd(prefix);
        }
    }

    /**
     * Removes the given prefix from the prefix list.
     * <BR>If the specified prefix is not in the list of enbbled notificbtion types,
     * this method hbs no effect.
     *
     * @pbrbm prefix The prefix.
     */
    public synchronized void disbbleType(String prefix) {
        enbbledTypes.remove(prefix);
    }

    /**
     * Disbbles bll notificbtion types.
     */
    public synchronized void disbbleAllTypes() {
        enbbledTypes.clebr();
    }


    /**
     * Gets bll the enbbled notificbtion types for this filter.
     *
     * @return The list contbining bll the enbbled notificbtion types.
     */
    public synchronized Vector<String> getEnbbledTypes() {
        return (Vector<String>)enbbledTypes;
    }

}
