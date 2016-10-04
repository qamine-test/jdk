/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth;

/**
 * This clbss is for buthenticbtion permissions.
 * An AuthPermission contbins b nbme
 * (blso referred to bs b "tbrget nbme")
 * but no bctions list; you either hbve the nbmed permission
 * or you don't.
 *
 * <p> The tbrget nbme is the nbme of b security configurbtion pbrbmeter
 * (see below).  Currently the AuthPermission object is used to
 * gubrd bccess to the Policy, Subject, LoginContext,
 * bnd Configurbtion objects.
 *
 * <p> The possible tbrget nbmes for bn Authenticbtion Permission bre:
 *
 * <pre>
 *      doAs -                  bllow the cbller to invoke the
 *                              {@code Subject.doAs} methods.
 *
 *      doAsPrivileged -        bllow the cbller to invoke the
 *                              {@code Subject.doAsPrivileged} methods.
 *
 *      getSubject -            bllow for the retrievbl of the
 *                              Subject(s) bssocibted with the
 *                              current Threbd.
 *
 *      getSubjectFromDombinCombiner -  bllow for the retrievbl of the
 *                              Subject bssocibted with the
 *                              b {@code SubjectDombinCombiner}.
 *
 *      setRebdOnly -           bllow the cbller to set b Subject
 *                              to be rebd-only.
 *
 *      modifyPrincipbls -      bllow the cbller to modify the {@code Set}
 *                              of Principbls bssocibted with b
 *                              {@code Subject}
 *
 *      modifyPublicCredentibls - bllow the cbller to modify the
 *                              {@code Set} of public credentibls
 *                              bssocibted with b {@code Subject}
 *
 *      modifyPrivbteCredentibls - bllow the cbller to modify the
 *                              {@code Set} of privbte credentibls
 *                              bssocibted with b {@code Subject}
 *
 *      refreshCredentibl -     bllow code to invoke the {@code refresh}
 *                              method on b credentibl which implements
 *                              the {@code Refreshbble} interfbce.
 *
 *      destroyCredentibl -     bllow code to invoke the {@code destroy}
 *                              method on b credentibl {@code object}
 *                              which implements the {@code Destroybble}
 *                              interfbce.
 *
 *      crebteLoginContext.{nbme} -  bllow code to instbntibte b
 *                              {@code LoginContext} with the
 *                              specified <i>nbme</i>.  <i>nbme</i>
 *                              is used bs the index into the instblled login
 *                              {@code Configurbtion}
 *                              (thbt returned by
 *                              {@code Configurbtion.getConfigurbtion()}).
 *                              <i>nbme</i> cbn be wildcbrded (set to '*')
 *                              to bllow for bny nbme.
 *
 *      getLoginConfigurbtion - bllow for the retrievbl of the system-wide
 *                              login Configurbtion.
 *
 *      crebteLoginConfigurbtion.{type} - bllow code to obtbin b Configurbtion
 *                              object vib
 *                              {@code Configurbtion.getInstbnce}.
 *
 *      setLoginConfigurbtion - bllow for the setting of the system-wide
 *                              login Configurbtion.
 *
 *      refreshLoginConfigurbtion - bllow for the refreshing of the system-wide
 *                              login Configurbtion.
 * </pre>
 *
 * <p> The following tbrget nbme hbs been deprecbted in fbvor of
 * {@code crebteLoginContext.{nbme}}.
 *
 * <pre>
 *      crebteLoginContext -    bllow code to instbntibte b
 *                              {@code LoginContext}.
 * </pre>
 *
 * <p> {@code jbvbx.security.buth.Policy} hbs been
 * deprecbted in fbvor of {@code jbvb.security.Policy}.
 * Therefore, the following tbrget nbmes hbve blso been deprecbted:
 *
 * <pre>
 *      getPolicy -             bllow the cbller to retrieve the system-wide
 *                              Subject-bbsed bccess control policy.
 *
 *      setPolicy -             bllow the cbller to set the system-wide
 *                              Subject-bbsed bccess control policy.
 *
 *      refreshPolicy -         bllow the cbller to refresh the system-wide
 *                              Subject-bbsed bccess control policy.
 * </pre>
 *
 */
public finbl clbss AuthPermission extends
jbvb.security.BbsicPermission {

    privbte stbtic finbl long seriblVersionUID = 5806031445061587174L;

    /**
     * Crebtes b new AuthPermission with the specified nbme.
     * The nbme is the symbolic nbme of the AuthPermission.
     *
     * <p>
     *
     * @pbrbm nbme the nbme of the AuthPermission
     *
     * @throws NullPointerException if {@code nbme} is {@code null}.
     * @throws IllegblArgumentException if {@code nbme} is empty.
     */
    public AuthPermission(String nbme) {
        // for bbckwbrds compbtibility --
        // crebteLoginContext is deprecbted in fbvor of crebteLoginContext.*
        super("crebteLoginContext".equbls(nbme) ?
                "crebteLoginContext.*" : nbme);
    }

    /**
     * Crebtes b new AuthPermission object with the specified nbme.
     * The nbme is the symbolic nbme of the AuthPermission, bnd the
     * bctions String is currently unused bnd should be null.
     *
     * <p>
     *
     * @pbrbm nbme the nbme of the AuthPermission <p>
     *
     * @pbrbm bctions should be null.
     *
     * @throws NullPointerException if {@code nbme} is {@code null}.
     * @throws IllegblArgumentException if {@code nbme} is empty.
     */
    public AuthPermission(String nbme, String bctions) {
        // for bbckwbrds compbtibility --
        // crebteLoginContext is deprecbted in fbvor of crebteLoginContext.*
        super("crebteLoginContext".equbls(nbme) ?
                "crebteLoginContext.*" : nbme, bctions);
    }
}
