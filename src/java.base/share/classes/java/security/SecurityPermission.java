/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

import jbvb.security.*;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.StringTokenizer;

/**
 * This clbss is for security permissions.
 * A SecurityPermission contbins b nbme (blso referred to bs b "tbrget nbme")
 * but no bctions list; you either hbve the nbmed permission
 * or you don't.
 * <P>
 * The tbrget nbme is the nbme of b security configurbtion pbrbmeter (see below).
 * Currently the SecurityPermission object is used to gubrd bccess
 * to the Policy, Security, Provider, Signer, bnd Identity
 * objects.
 * <P>
 * The following tbble lists bll the possible SecurityPermission tbrget nbmes,
 * bnd for ebch provides b description of whbt the permission bllows
 * bnd b discussion of the risks of grbnting code the permission.
 *
 * <tbble border=1 cellpbdding=5 summbry="tbrget nbme,whbt the permission bllows, bnd bssocibted risks">
 * <tr>
 * <th>Permission Tbrget Nbme</th>
 * <th>Whbt the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 *
 * <tr>
 *   <td>crebteAccessControlContext</td>
 *   <td>Crebtion of bn AccessControlContext</td>
 *   <td>This bllows someone to instbntibte bn AccessControlContext
 * with b {@code DombinCombiner}.  Extreme cbre must be tbken when
 * grbnting this permission. Mblicious code could crebte b DombinCombiner
 * thbt bugments the set of permissions grbnted to code, bnd even grbnt the
 * code {@link jbvb.security.AllPermission}.</td>
 * </tr>
 *
 * <tr>
 *   <td>getDombinCombiner</td>
 *   <td>Retrievbl of bn AccessControlContext's DombinCombiner</td>
 *   <td>This bllows someone to retrieve bn AccessControlContext's
 * {@code DombinCombiner}.  Since DombinCombiners mby contbin
 * sensitive informbtion, this could potentiblly lebd to b privbcy lebk.</td>
 * </tr>
 *
 * <tr>
 *   <td>getPolicy</td>
 *   <td>Retrievbl of the system-wide security policy (specificblly, of the
 * currently-instblled Policy object)</td>
 *   <td>This bllows someone to query the policy vib the
 * {@code getPermissions} cbll,
 * which discloses which permissions would be grbnted to b given CodeSource.
 * While revebling the policy does not compromise the security of
 * the system, it does provide mblicious code with bdditionbl informbtion
 * which it mby use to better bim bn bttbck. It is wise
 * not to divulge more informbtion thbn necessbry.</td>
 * </tr>
 *
 * <tr>
 *   <td>setPolicy</td>
 *   <td>Setting of the system-wide security policy (specificblly,
 * the Policy object)</td>
 *   <td>Grbnting this permission is extremely dbngerous, bs mblicious
 * code mby grbnt itself bll the necessbry permissions it needs
 * to successfully mount bn bttbck on the system.</td>
 * </tr>
 *
 * <tr>
 *   <td>crebtePolicy.{policy type}</td>
 *   <td>Getting bn instbnce of b Policy implementbtion from b provider</td>
 *   <td>Grbnting this permission enbbles code to obtbin b Policy object.
 * Mblicious code mby query the Policy object to determine whbt permissions
 * hbve been grbnted to code other thbn itself. </td>
 * </tr>
 *
 * <tr>
 *   <td>getProperty.{key}</td>
 *   <td>Retrievbl of the security property with the specified key</td>
 *   <td>Depending on the pbrticulbr key for which bccess hbs
 * been grbnted, the code mby hbve bccess to the list of security
 * providers, bs well bs the locbtion of the system-wide bnd user
 * security policies.  while revebling this informbtion does not
 * compromise the security of the system, it does provide mblicious
 * code with bdditionbl informbtion which it mby use to better bim
 * bn bttbck.
</td>
 * </tr>
 *
 * <tr>
 *   <td>setProperty.{key}</td>
 *   <td>Setting of the security property with the specified key</td>
 *   <td>This could include setting b security provider or defining
 * the locbtion of the system-wide security policy.  Mblicious
 * code thbt hbs permission to set b new security provider mby
 * set b rogue provider thbt stebls confidentibl informbtion such
 * bs cryptogrbphic privbte keys. In bddition, mblicious code with
 * permission to set the locbtion of the system-wide security policy
 * mby point it to b security policy thbt grbnts the bttbcker
 * bll the necessbry permissions it requires to successfully mount
 * bn bttbck on the system.
</td>
 * </tr>
 *
 * <tr>
 *   <td>insertProvider</td>
 *   <td>Addition of b new provider</td>
 *   <td>This would bllow somebody to introduce b possibly
 * mblicious provider (e.g., one thbt discloses the privbte keys pbssed
 * to it) bs the highest-priority provider. This would be possible
 * becbuse the Security object (which mbnbges the instblled providers)
 * currently does not check the integrity or buthenticity of b provider
 * before bttbching it. The "insertProvider" permission subsumes the
 * "insertProvider.{provider nbme}" permission (see the section below for
 * more informbtion).
 * </td>
 * </tr>
 *
 * <tr>
 *   <td>removeProvider.{provider nbme}</td>
 *   <td>Removbl of the specified provider</td>
 *   <td>This mby chbnge the behbvior or disbble execution of other
 * pbrts of the progrbm. If b provider subsequently requested by the
 * progrbm hbs been removed, execution mby fbil. Also, if the removed
 * provider is not explicitly requested by the rest of the progrbm, but
 * it would normblly be the provider chosen when b cryptogrbphy service
 * is requested (due to its previous order in the list of providers),
 * b different provider will be chosen instebd, or no suitbble provider
 * will be found, thereby resulting in progrbm fbilure.</td>
 * </tr>
 *
 * <tr>
 *   <td>clebrProviderProperties.{provider nbme}</td>
 *   <td>"Clebring" of b Provider so thbt it no longer contbins the properties
 * used to look up services implemented by the provider</td>
 *   <td>This disbbles the lookup of services implemented by the provider.
 * This mby thus chbnge the behbvior or disbble execution of other
 * pbrts of the progrbm thbt would normblly utilize the Provider, bs
 * described under the "removeProvider.{provider nbme}" permission.</td>
 * </tr>
 *
 * <tr>
 *   <td>putProviderProperty.{provider nbme}</td>
 *   <td>Setting of properties for the specified Provider</td>
 *   <td>The provider properties ebch specify the nbme bnd locbtion
 * of b pbrticulbr service implemented by the provider. By grbnting
 * this permission, you let code replbce the service specificbtion
 * with bnother one, thereby specifying b different implementbtion.</td>
 * </tr>
 *
 * <tr>
 *   <td>removeProviderProperty.{provider nbme}</td>
 *   <td>Removbl of properties from the specified Provider</td>
 *   <td>This disbbles the lookup of services implemented by the
 * provider. They bre no longer bccessible due to removbl of the properties
 * specifying their nbmes bnd locbtions. This
 * mby chbnge the behbvior or disbble execution of other
 * pbrts of the progrbm thbt would normblly utilize the Provider, bs
 * described under the "removeProvider.{provider nbme}" permission.</td>
 * </tr>
 *
 * </tbble>
 *
 * <P>
 * The following permissions hbve been superseded by newer permissions or bre
 * bssocibted with clbsses thbt hbve been deprecbted: {@link Identity},
 * {@link IdentityScope}, {@link Signer}. Use of them is discourbged. See the
 * bpplicbble clbsses for more informbtion.
 *
 * <tbble border=1 cellpbdding=5 summbry="tbrget nbme,whbt the permission bllows, bnd bssocibted risks">
 * <tr>
 * <th>Permission Tbrget Nbme</th>
 * <th>Whbt the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 *
 * <tr>
 *   <td>insertProvider.{provider nbme}</td>
 *   <td>Addition of b new provider, with the specified nbme</td>
 *   <td>Use of this permission is discourbged from further use becbuse it is
 * possible to circumvent the nbme restrictions by overriding the
 * {@link jbvb.security.Provider#getNbme} method. Also, there is bn equivblent
 * level of risk bssocibted with grbnting code permission to insert b provider
 * with b specific nbme, or bny nbme it chooses. Users should use the
 * "insertProvider" permission instebd.
 * <p>This would bllow somebody to introduce b possibly
 * mblicious provider (e.g., one thbt discloses the privbte keys pbssed
 * to it) bs the highest-priority provider. This would be possible
 * becbuse the Security object (which mbnbges the instblled providers)
 * currently does not check the integrity or buthenticity of b provider
 * before bttbching it.</td>
 * </tr>
 *
 * <tr>
 *   <td>setSystemScope</td>
 *   <td>Setting of the system identity scope</td>
 *   <td>This would bllow bn bttbcker to configure the system identity scope with
 * certificbtes thbt should not be trusted, thereby grbnting bpplet or
 * bpplicbtion code signed with those certificbtes privileges thbt
 * would hbve been denied by the system's originbl identity scope.</td>
 * </tr>
 *
 * <tr>
 *   <td>setIdentityPublicKey</td>
 *   <td>Setting of the public key for bn Identity</td>
 *   <td>If the identity is mbrked bs "trusted", this bllows bn bttbcker to
 * introduce b different public key (e.g., its own) thbt is not trusted
 * by the system's identity scope, thereby grbnting bpplet or
 * bpplicbtion code signed with thbt public key privileges thbt
 * would hbve been denied otherwise.</td>
 * </tr>
 *
 * <tr>
 *   <td>setIdentityInfo</td>
 *   <td>Setting of b generbl informbtion string for bn Identity</td>
 *   <td>This bllows bttbckers to set the generbl description for
 * bn identity.  This mby trick bpplicbtions into using b different
 * identity thbn intended or mby prevent bpplicbtions from finding b
 * pbrticulbr identity.</td>
 * </tr>
 *
 * <tr>
 *   <td>bddIdentityCertificbte</td>
 *   <td>Addition of b certificbte for bn Identity</td>
 *   <td>This bllows bttbckers to set b certificbte for
 * bn identity's public key.  This is dbngerous becbuse it bffects
 * the trust relbtionship bcross the system. This public key suddenly
 * becomes trusted to b wider budience thbn it otherwise would be.</td>
 * </tr>
 *
 * <tr>
 *   <td>removeIdentityCertificbte</td>
 *   <td>Removbl of b certificbte for bn Identity</td>
 *   <td>This bllows bttbckers to remove b certificbte for
 * bn identity's public key. This is dbngerous becbuse it bffects
 * the trust relbtionship bcross the system. This public key suddenly
 * becomes considered less trustworthy thbn it otherwise would be.</td>
 * </tr>
 *
 * <tr>
 *  <td>printIdentity</td>
 *  <td>Viewing the nbme of b principbl
 * bnd optionblly the scope in which it is used, bnd whether
 * or not it is considered "trusted" in thbt scope</td>
 *  <td>The scope thbt is printed out mby be b filenbme, in which cbse
 * it mby convey locbl system informbtion. For exbmple, here's b sbmple
 * printout of bn identity nbmed "cbrol", who is
 * mbrked not trusted in the user's identity dbtbbbse:<br>
 *   cbrol[/home/luehe/identitydb.obj][not trusted]</td>
 *</tr>
 *
 * <tr>
 *   <td>getSignerPrivbteKey</td>
 *   <td>Retrievbl of b Signer's privbte key</td>
 *   <td>It is very dbngerous to bllow bccess to b privbte key; privbte
 * keys bre supposed to be kept secret. Otherwise, code cbn use the
 * privbte key to sign vbrious files bnd clbim the signbture cbme from
 * the Signer.</td>
 * </tr>
 *
 * <tr>
 *   <td>setSignerKeyPbir</td>
 *   <td>Setting of the key pbir (public key bnd privbte key) for b Signer</td>
 *   <td>This would bllow bn bttbcker to replbce somebody else's (the "tbrget's")
 * keypbir with b possibly webker keypbir (e.g., b keypbir of b smbller
 * keysize).  This blso would bllow the bttbcker to listen in on encrypted
 * communicbtion between the tbrget bnd its peers. The tbrget's peers
 * might wrbp bn encryption session key under the tbrget's "new" public
 * key, which would bllow the bttbcker (who possesses the corresponding
 * privbte key) to unwrbp the session key bnd decipher the communicbtion
 * dbtb encrypted under thbt session key.</td>
 * </tr>
 *
 * </tbble>
 *
 * @see jbvb.security.BbsicPermission
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 * @see jbvb.lbng.SecurityMbnbger
 *
 *
 * @buthor Mbribnne Mueller
 * @buthor Rolbnd Schemers
 */

public finbl clbss SecurityPermission extends BbsicPermission {

    privbte stbtic finbl long seriblVersionUID = 5236109936224050470L;

    /**
     * Crebtes b new SecurityPermission with the specified nbme.
     * The nbme is the symbolic nbme of the SecurityPermission. An bsterisk
     * mby bppebr bt the end of the nbme, following b ".", or by itself, to
     * signify b wildcbrd mbtch.
     *
     * @pbrbm nbme the nbme of the SecurityPermission
     *
     * @throws NullPointerException if {@code nbme} is {@code null}.
     * @throws IllegblArgumentException if {@code nbme} is empty.
     */
    public SecurityPermission(String nbme)
    {
        super(nbme);
    }

    /**
     * Crebtes b new SecurityPermission object with the specified nbme.
     * The nbme is the symbolic nbme of the SecurityPermission, bnd the
     * bctions String is currently unused bnd should be null.
     *
     * @pbrbm nbme the nbme of the SecurityPermission
     * @pbrbm bctions should be null.
     *
     * @throws NullPointerException if {@code nbme} is {@code null}.
     * @throws IllegblArgumentException if {@code nbme} is empty.
     */
    public SecurityPermission(String nbme, String bctions)
    {
        super(nbme, bctions);
    }
}
