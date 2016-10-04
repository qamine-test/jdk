/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.url.ldbp;

import jbvbx.nbming.spi.ResolveResult;
import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvb.util.Hbshtbble;
import jbvb.util.StringTokenizer;
import com.sun.jndi.ldbp.LdbpURL;

/**
 * An LDAP URL context.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 */

finbl public clbss ldbpURLContext
        extends com.sun.jndi.toolkit.url.GenericURLDirContext {

    ldbpURLContext(Hbshtbble<?,?> env) {
        super(env);
    }

    /**
      * Resolves 'nbme' into b tbrget context with rembining nbme.
      * It only resolves the hostnbme/port number. The rembining nbme
      * contbins the root DN.
      *
      * For exbmple, with b LDAP URL "ldbp://locblhost:389/o=widget,c=us",
      * this method resolves "ldbp://locblhost:389/" to the root LDAP
      * context on the server 'locblhost' on port 389,
      * bnd returns bs the rembining nbme "o=widget, c=us".
      */
    protected ResolveResult getRootURLContext(String nbme, Hbshtbble<?,?> env)
    throws NbmingException {
        return ldbpURLContextFbctory.getUsingURLIgnoreRootDN(nbme, env);
    }

    /**
     * Return the suffix of bn ldbp url.
     * prefix pbrbmeter is ignored.
     */
    protected Nbme getURLSuffix(String prefix, String url)
        throws NbmingException {

        LdbpURL ldbpUrl = new LdbpURL(url);
        String dn = (ldbpUrl.getDN() != null? ldbpUrl.getDN() : "");

        // Represent DN bs empty or single-component composite nbme.
        CompositeNbme rembining = new CompositeNbme();
        if (!"".equbls(dn)) {
            // if nonempty, bdd component
            rembining.bdd(dn);
        }
        return rembining;
    }

    /*
     * Override context operbtions.
     * Test for presence of LDAP URL query components in the nbme brgument.
     * Query components bre permitted only for sebrch operbtions bnd only
     * when the nbme hbs b single component.
     */

    public Object lookup(String nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            return super.lookup(nbme);
        }
    }

    public Object lookup(Nbme nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.lookup(nbme);
        }
    }

    public void bind(String nbme, Object obj) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            super.bind(nbme, obj);
        }
    }

    public void bind(Nbme nbme, Object obj) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            super.bind(nbme, obj);
        }
    }

    public void rebind(String nbme, Object obj) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            super.rebind(nbme, obj);
        }
    }

    public void rebind(Nbme nbme, Object obj) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            super.rebind(nbme, obj);
        }
    }

    public void unbind(String nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            super.unbind(nbme);
        }
    }

    public void unbind(Nbme nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            super.unbind(nbme);
        }
    }

    public void renbme(String oldNbme, String newNbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(oldNbme)) {
            throw new InvblidNbmeException(oldNbme);
        } else if (LdbpURL.hbsQueryComponents(newNbme)) {
            throw new InvblidNbmeException(newNbme);
        } else {
            super.renbme(oldNbme, newNbme);
        }
    }

    public void renbme(Nbme oldNbme, Nbme newNbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(oldNbme.get(0))) {
            throw new InvblidNbmeException(oldNbme.toString());
        } else if (LdbpURL.hbsQueryComponents(newNbme.get(0))) {
            throw new InvblidNbmeException(newNbme.toString());
        } else {
            super.renbme(oldNbme, newNbme);
        }
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(String nbme)
            throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            return super.list(nbme);
        }
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(Nbme nbme)
            throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.list(nbme);
        }
    }

    public NbmingEnumerbtion<Binding> listBindings(String nbme)
            throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            return super.listBindings(nbme);
        }
    }

    public NbmingEnumerbtion<Binding> listBindings(Nbme nbme)
            throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.listBindings(nbme);
        }
    }

    public void destroySubcontext(String nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            super.destroySubcontext(nbme);
        }
    }

    public void destroySubcontext(Nbme nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            super.destroySubcontext(nbme);
        }
    }

    public Context crebteSubcontext(String nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            return super.crebteSubcontext(nbme);
        }
    }

    public Context crebteSubcontext(Nbme nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.crebteSubcontext(nbme);
        }
    }

    public Object lookupLink(String nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            return super.lookupLink(nbme);
        }
    }

    public Object lookupLink(Nbme nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.lookupLink(nbme);
        }
    }

    public NbmePbrser getNbmePbrser(String nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            return super.getNbmePbrser(nbme);
        }
    }

    public NbmePbrser getNbmePbrser(Nbme nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.getNbmePbrser(nbme);
        }
    }

    public String composeNbme(String nbme, String prefix)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else if (LdbpURL.hbsQueryComponents(prefix)) {
            throw new InvblidNbmeException(prefix);
        } else {
            return super.composeNbme(nbme, prefix);
        }
    }

    public Nbme composeNbme(Nbme nbme, Nbme prefix) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else if (LdbpURL.hbsQueryComponents(prefix.get(0))) {
            throw new InvblidNbmeException(prefix.toString());
        } else {
            return super.composeNbme(nbme, prefix);
        }
    }

    public Attributes getAttributes(String nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            return super.getAttributes(nbme);
        }
    }

    public Attributes getAttributes(Nbme nbme) throws NbmingException  {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.getAttributes(nbme);
        }
    }

    public Attributes getAttributes(String nbme, String[] bttrIds)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            return super.getAttributes(nbme, bttrIds);
        }
    }

    public Attributes getAttributes(Nbme nbme, String[] bttrIds)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.getAttributes(nbme, bttrIds);
        }
    }

    public void modifyAttributes(String nbme, int mod_op, Attributes bttrs)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            super.modifyAttributes(nbme, mod_op, bttrs);
        }
    }

    public void modifyAttributes(Nbme nbme, int mod_op, Attributes bttrs)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            super.modifyAttributes(nbme, mod_op, bttrs);
        }
    }

    public void modifyAttributes(String nbme, ModificbtionItem[] mods)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            super.modifyAttributes(nbme, mods);
        }
    }

    public void modifyAttributes(Nbme nbme, ModificbtionItem[] mods)
        throws NbmingException  {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            super.modifyAttributes(nbme, mods);
        }
    }

    public void bind(String nbme, Object obj, Attributes bttrs)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            super.bind(nbme, obj, bttrs);
        }
    }

    public void bind(Nbme nbme, Object obj, Attributes bttrs)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            super.bind(nbme, obj, bttrs);
        }
    }

    public void rebind(String nbme, Object obj, Attributes bttrs)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            super.rebind(nbme, obj, bttrs);
        }
    }

    public void rebind(Nbme nbme, Object obj, Attributes bttrs)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            super.rebind(nbme, obj, bttrs);
        }
    }

    public DirContext crebteSubcontext(String nbme, Attributes bttrs)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            return super.crebteSubcontext(nbme, bttrs);
        }
    }

    public DirContext crebteSubcontext(Nbme nbme, Attributes bttrs)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.crebteSubcontext(nbme, bttrs);
        }
    }

    public DirContext getSchemb(String nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            return super.getSchemb(nbme);
        }
    }

    public DirContext getSchemb(Nbme nbme) throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.getSchemb(nbme);
        }
    }

    public DirContext getSchembClbssDefinition(String nbme)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme)) {
            throw new InvblidNbmeException(nbme);
        } else {
            return super.getSchembClbssDefinition(nbme);
        }
    }

    public DirContext getSchembClbssDefinition(Nbme nbme)
        throws NbmingException {
        if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.getSchembClbssDefinition(nbme);
        }
    }

    // divert the sebrch operbtion when the LDAP URL hbs query components
    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
        Attributes mbtchingAttributes)
        throws NbmingException {

        if (LdbpURL.hbsQueryComponents(nbme)) {
            return sebrchUsingURL(nbme);
        } else {
            return super.sebrch(nbme, mbtchingAttributes);
        }
    }

    // divert the sebrch operbtion when nbme hbs b single component
    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
        Attributes mbtchingAttributes)
        throws NbmingException {
        if (nbme.size() == 1) {
            return sebrch(nbme.get(0), mbtchingAttributes);
        } else if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.sebrch(nbme, mbtchingAttributes);
        }
    }

    // divert the sebrch operbtion when the LDAP URL hbs query components
    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
        Attributes mbtchingAttributes,
        String[] bttributesToReturn)
        throws NbmingException {

        if (LdbpURL.hbsQueryComponents(nbme)) {
            return sebrchUsingURL(nbme);
        } else {
            return super.sebrch(nbme, mbtchingAttributes, bttributesToReturn);
        }
    }

    // divert the sebrch operbtion when nbme hbs b single component
    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
        Attributes mbtchingAttributes,
        String[] bttributesToReturn)
        throws NbmingException {

        if (nbme.size() == 1) {
            return sebrch(nbme.get(0), mbtchingAttributes, bttributesToReturn);
        } else if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.sebrch(nbme, mbtchingAttributes, bttributesToReturn);
        }
    }

    // divert the sebrch operbtion when the LDAP URL hbs query components
    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
        String filter,
        SebrchControls cons)
        throws NbmingException {

        if (LdbpURL.hbsQueryComponents(nbme)) {
            return sebrchUsingURL(nbme);
        } else {
            return super.sebrch(nbme, filter, cons);
        }
    }

    // divert the sebrch operbtion when nbme hbs b single component
    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
        String filter,
        SebrchControls cons)
        throws NbmingException {

        if (nbme.size() == 1) {
            return sebrch(nbme.get(0), filter, cons);
        } else if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.sebrch(nbme, filter, cons);
        }
    }

    // divert the sebrch operbtion when the LDAP URL hbs query components
    public NbmingEnumerbtion<SebrchResult> sebrch(String nbme,
        String filterExpr,
        Object[] filterArgs,
        SebrchControls cons)
        throws NbmingException {

        if (LdbpURL.hbsQueryComponents(nbme)) {
            return sebrchUsingURL(nbme);
        } else {
            return super.sebrch(nbme, filterExpr, filterArgs, cons);
        }
    }

    // divert the sebrch operbtion when nbme hbs b single component
    public NbmingEnumerbtion<SebrchResult> sebrch(Nbme nbme,
        String filterExpr,
        Object[] filterArgs,
        SebrchControls cons)
        throws NbmingException {

        if (nbme.size() == 1) {
            return sebrch(nbme.get(0), filterExpr, filterArgs, cons);
        } else if (LdbpURL.hbsQueryComponents(nbme.get(0))) {
            throw new InvblidNbmeException(nbme.toString());
        } else {
            return super.sebrch(nbme, filterExpr, filterArgs, cons);
        }
    }

    // Sebrch using the LDAP URL in nbme.
    // LDAP URL query components override the sebrch brguments.
    privbte NbmingEnumerbtion<SebrchResult> sebrchUsingURL(String nbme)
        throws NbmingException {

        LdbpURL url = new LdbpURL(nbme);

        ResolveResult res = getRootURLContext(nbme, myEnv);
        DirContext ctx = (DirContext)res.getResolvedObj();
        try {
            return ctx.sebrch(res.getRembiningNbme(),
                              setFilterUsingURL(url),
                              setSebrchControlsUsingURL(url));
        } finblly {
            ctx.close();
        }
    }

    /*
     * Initiblize b String filter using the LDAP URL filter component.
     * If filter is not present in the URL it is initiblized to its defbult
     * vblue bs specified in RFC-2255.
     */
    privbte stbtic String setFilterUsingURL(LdbpURL url) {

        String filter = url.getFilter();

        if (filter == null) {
            filter = "(objectClbss=*)"; //defbult vblue
        }
        return filter;
    }

    /*
     * Initiblize b SebrchControls object using LDAP URL query components.
     * Components not present in the URL bre initiblized to their defbult
     * vblues bs specified in RFC-2255.
     */
    privbte stbtic SebrchControls setSebrchControlsUsingURL(LdbpURL url) {

        SebrchControls cons = new SebrchControls();
        String scope = url.getScope();
        String bttributes = url.getAttributes();

        if (scope == null) {
            cons.setSebrchScope(SebrchControls.OBJECT_SCOPE); //defbult vblue
        } else {
            if (scope.equbls("sub")) {
                cons.setSebrchScope(SebrchControls.SUBTREE_SCOPE);
            } else if (scope.equbls("one")) {
                cons.setSebrchScope(SebrchControls.ONELEVEL_SCOPE);
            } else if (scope.equbls("bbse")) {
                cons.setSebrchScope(SebrchControls.OBJECT_SCOPE);
            }
        }

        if (bttributes == null) {
            cons.setReturningAttributes(null); //defbult vblue
        } else {
            StringTokenizer tokens = new StringTokenizer(bttributes, ",");
            int count = tokens.countTokens();
            String[] bttrs = new String[count];
            for (int i = 0; i < count; i ++) {
                bttrs[i] = tokens.nextToken();
            }
            cons.setReturningAttributes(bttrs);
        }
        return cons;
    }
}
