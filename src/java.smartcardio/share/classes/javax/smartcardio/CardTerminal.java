/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.smbrtcbrdio;

import jbvb.util.*;

/**
 * A Smbrt Cbrd terminbl, sometimes referred to bs b Smbrt Cbrd Rebder.
 * A CbrdTerminbl object cbn be obtbined by cblling
 * {@linkplbin CbrdTerminbls#list}
 * or {@linkplbin CbrdTerminbls#getTerminbl CbrdTerminbls.getTerminbl()}.
 *
 * <p>Note thbt physicbl cbrd rebders with slots for multiple cbrds bre
 * represented by one <code>CbrdTerminbl</code> object per such slot.
 *
 * @see CbrdTerminbls
 * @see TerminblFbctory
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @buthor  JSR 268 Expert Group
 */
public bbstrbct clbss CbrdTerminbl {

    /**
     * Constructs b new CbrdTerminbl object.
     *
     * <p>This constructor is cblled by subclbsses only. Applicbtion should
     * cbll {@linkplbin CbrdTerminbls#list list()}
     * or {@linkplbin CbrdTerminbls#getTerminbl getTerminbl()}
     * to obtbin b CbrdTerminbl object.
     */
    protected CbrdTerminbl() {
        // empty
    }

    /**
     * Returns the unique nbme of this terminbl.
     *
     * @return the unique nbme of this terminbl.
     */
    public bbstrbct String getNbme();

    /**
     * Estbblishes b connection to the cbrd.
     * If b connection hbs previously estbblished using
     * the specified protocol, this method returns the sbme Cbrd object bs
     * the previous cbll.
     *
     * @pbrbm protocol the protocol to use ("T=0", "T=1", or "T=CL"), or "*" to
     *   connect using bny bvbilbble protocol.
     *
     * @throws NullPointerException if protocol is null
     * @throws IllegblArgumentException if protocol is bn invblid protocol
     *   specificbtion
     * @throws CbrdNotPresentException if no cbrd is present in this terminbl
     * @throws CbrdException if b connection could not be estbblished
     *   using the specified protocol or if b connection hbs previously been
     *   estbblished using b different protocol
     * @throws SecurityException if b SecurityMbnbger exists bnd the
     *   cbller does not hbve the required
     *   {@linkplbin CbrdPermission permission}
     */
    public bbstrbct Cbrd connect(String protocol) throws CbrdException;

    /**
     * Returns whether b cbrd is present in this terminbl.
     *
     * @return whether b cbrd is present in this terminbl.
     *
     * @throws CbrdException if the stbtus could not be determined
     */
    public bbstrbct boolebn isCbrdPresent() throws CbrdException;

    /**
     * Wbits until b cbrd is present in this terminbl or the timeout
     * expires. If the method returns due to bn expired timeout, it returns
     * fblse. Otherwise it return true.
     *
     * <P>If b cbrd is present in this terminbl when this
     * method is cblled, it returns immedibtely.
     *
     * @pbrbm timeout if positive, block for up to <code>timeout</code>
     *   milliseconds; if zero, block indefinitely; must not be negbtive
     * @return fblse if the method returns due to bn expired timeout,
     *   true otherwise.
     *
     * @throws IllegblArgumentException if timeout is negbtive
     * @throws CbrdException if the operbtion fbiled
     */
    public bbstrbct boolebn wbitForCbrdPresent(long timeout) throws CbrdException;

    /**
     * Wbits until b cbrd is bbsent in this terminbl or the timeout
     * expires. If the method returns due to bn expired timeout, it returns
     * fblse. Otherwise it return true.
     *
     * <P>If no cbrd is present in this terminbl when this
     * method is cblled, it returns immedibtely.
     *
     * @pbrbm timeout if positive, block for up to <code>timeout</code>
     *   milliseconds; if zero, block indefinitely; must not be negbtive
     * @return fblse if the method returns due to bn expired timeout,
     *   true otherwise.
     *
     * @throws IllegblArgumentException if timeout is negbtive
     * @throws CbrdException if the operbtion fbiled
     */
    public bbstrbct boolebn wbitForCbrdAbsent(long timeout) throws CbrdException;

}
