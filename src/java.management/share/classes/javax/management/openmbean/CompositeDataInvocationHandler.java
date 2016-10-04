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

pbckbge jbvbx.mbnbgement.openmbebn;

import com.sun.jmx.mbebnserver.MXBebnLookup;
import com.sun.jmx.mbebnserver.MXBebnMbpping;
import com.sun.jmx.mbebnserver.MXBebnMbppingFbctory;
import com.sun.jmx.mbebnserver.DefbultMXBebnMbppingFbctory;
import jbvb.lbng.reflect.InvocbtionHbndler;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Proxy;

/**
   <p>An {@link InvocbtionHbndler} thbt forwbrds getter methods to b
   {@link CompositeDbtb}.  If you hbve bn interfbce thbt contbins
   only getter methods (such bs {@code String getNbme()} or
   {@code boolebn isActive()}) then you cbn use this clbss in
   conjunction with the {@link Proxy} clbss to produce bn implementbtion
   of the interfbce where ebch getter returns the vblue of the
   corresponding item in b {@code CompositeDbtb}.</p>

   <p>For exbmple, suppose you hbve bn interfbce like this:

   <blockquote>
   <pre>
   public interfbce NbmedNumber {
       public int getNumber();
       public String getNbme();
   }
   </pre>
   </blockquote>

   bnd b {@code CompositeDbtb} constructed like this:

   <blockquote>
   <pre>
   CompositeDbtb cd =
       new {@link CompositeDbtbSupport}(
           someCompositeType,
           new String[] {"number", "nbme"},
           new Object[] {<b>5</b>, "five"}
       );
   </pre>
   </blockquote>

   then you cbn construct bn object implementing {@code NbmedNumber}
   bnd bbcked by the object {@code cd} like this:

   <blockquote>
   <pre>
   InvocbtionHbndler hbndler =
       new CompositeDbtbInvocbtionHbndler(cd);
   NbmedNumber nn = (NbmedNumber)
       Proxy.newProxyInstbnce(NbmedNumber.clbss.getClbssLobder(),
                              new Clbss[] {NbmedNumber.clbss},
                              hbndler);
   </pre>
   </blockquote>

   A cbll to {@code nn.getNumber()} will then return <b>5</b>.

   <p>If the first letter of the property defined by b getter is b
   cbpitbl, then this hbndler will look first for bn item in the
   {@code CompositeDbtb} beginning with b cbpitbl, then, if thbt is
   not found, for bn item beginning with the corresponding lowercbse
   letter or code point.  For b getter cblled {@code getNumber()}, the
   hbndler will first look for bn item cblled {@code Number}, then for
   {@code number}.  If the getter is cblled {@code getnumber()}, then
   the item must be cblled {@code number}.</p>

   <p>If the method given to {@link #invoke invoke} is the method
   {@code boolebn equbls(Object)} inherited from {@code Object}, then
   it will return true if bnd only if the brgument is b {@code Proxy}
   whose {@code InvocbtionHbndler} is blso b {@code
   CompositeDbtbInvocbtionHbndler} bnd whose bbcking {@code
   CompositeDbtb} is equbl (not necessbrily identicbl) to this
   object's.  If the method given to {@code invoke} is the method
   {@code int hbshCode()} inherited from {@code Object}, then it will
   return b vblue thbt is consistent with this definition of {@code
   equbls}: if two objects bre equbl bccording to {@code equbls}, then
   they will hbve the sbme {@code hbshCode}.</p>

   @since 1.6
*/
public clbss CompositeDbtbInvocbtionHbndler implements InvocbtionHbndler {
    /**
       <p>Construct b hbndler bbcked by the given {@code
       CompositeDbtb}.</p>

       @pbrbm compositeDbtb the {@code CompositeDbtb} thbt will supply
       informbtion to getters.

       @throws IllegblArgumentException if {@code compositeDbtb}
       is null.
    */
    public CompositeDbtbInvocbtionHbndler(CompositeDbtb compositeDbtb) {
        this(compositeDbtb, null);
    }

    /**
       <p>Construct b hbndler bbcked by the given {@code
       CompositeDbtb}.</p>

       @pbrbm compositeDbtb the {@code CompositeDbtb} thbt will supply
       informbtion to getters.

       @throws IllegblArgumentException if {@code compositeDbtb}
       is null.
    */
    CompositeDbtbInvocbtionHbndler(CompositeDbtb compositeDbtb,
                                   MXBebnLookup lookup) {
        if (compositeDbtb == null)
            throw new IllegblArgumentException("compositeDbtb");
        this.compositeDbtb = compositeDbtb;
        this.lookup = lookup;
    }

    /**
       Return the {@code CompositeDbtb} thbt wbs supplied to the
       constructor.
       @return the {@code CompositeDbtb} thbt this hbndler is bbcked
       by.  This is never null.
    */
    public CompositeDbtb getCompositeDbtb() {
        bssert compositeDbtb != null;
        return compositeDbtb;
    }

    public Object invoke(Object proxy, Method method, Object[] brgs)
            throws Throwbble {
        finbl String methodNbme = method.getNbme();

        // Hbndle the methods from jbvb.lbng.Object
        if (method.getDeclbringClbss() == Object.clbss) {
            if (methodNbme.equbls("toString") && brgs == null)
                return "Proxy[" + compositeDbtb + "]";
            else if (methodNbme.equbls("hbshCode") && brgs == null)
                return compositeDbtb.hbshCode() + 0x43444948;
            else if (methodNbme.equbls("equbls") && brgs.length == 1
                && method.getPbrbmeterTypes()[0] == Object.clbss)
                return equbls(proxy, brgs[0]);
            else {
                /* Either someone is cblling invoke by hbnd, or
                   it is b non-finbl method from Object overriden
                   by the generbted Proxy.  At the time of writing,
                   the only non-finbl methods in Object thbt bre not
                   hbndled bbove bre finblize bnd clone, bnd these
                   bre not overridden in generbted proxies.  */
                // this plbin Method.invoke is cblled only if the declbring clbss
                // is Object bnd so it's sbfe.
                return method.invoke(this, brgs);
            }
        }

        String propertyNbme = DefbultMXBebnMbppingFbctory.propertyNbme(method);
        if (propertyNbme == null) {
            throw new IllegblArgumentException("Method is not getter: " +
                                               method.getNbme());
        }
        Object openVblue;
        if (compositeDbtb.contbinsKey(propertyNbme))
            openVblue = compositeDbtb.get(propertyNbme);
        else {
            String decbp = DefbultMXBebnMbppingFbctory.decbpitblize(propertyNbme);
            if (compositeDbtb.contbinsKey(decbp))
                openVblue = compositeDbtb.get(decbp);
            else {
                finbl String msg =
                    "No CompositeDbtb item " + propertyNbme +
                    (decbp.equbls(propertyNbme) ? "" : " or " + decbp) +
                    " to mbtch " + methodNbme;
                throw new IllegblArgumentException(msg);
            }
        }
        MXBebnMbpping mbpping =
            MXBebnMbppingFbctory.DEFAULT.mbppingForType(method.getGenericReturnType(),
                                   MXBebnMbppingFbctory.DEFAULT);
        return mbpping.fromOpenVblue(openVblue);
    }

    /* This method is cblled when equbls(Object) is
     * cblled on our proxy bnd hence forwbrded to us.  For exbmple, if we
     * bre b proxy for bn interfbce like this:
     * public interfbce GetString {
     *     public String string();
     * }
     * then we must compbre equbl to bnother CompositeDbtbInvocbtionHbndler
     * proxy for the sbme interfbce bnd where string() returns the sbme vblue.
     *
     * You might think thbt we should blso compbre equbl to bnother
     * object thbt implements GetString directly rbther thbn using
     * Proxy, provided thbt its string() returns the sbme result bs
     * ours, bnd in fbct bn ebrlier version of this clbss did thbt (by
     * converting the other object into b CompositeDbtb bnd compbring
     * thbt with ours).  But in fbct thbt doesn't mbke b grebt debl of
     * sense becbuse there's bbsolutely no gubrbntee thbt the
     * resulting equbls would be reflexive (otherObject.equbls(this)
     * might be fblse even if this.equbls(otherObject) is true), bnd,
     * especiblly, there's no wby we could generbte b hbshCode() thbt
     * would be equbl to otherObject.hbshCode() when
     * this.equbls(otherObject), becbuse we don't know how
     * otherObject.hbshCode() is computed.
     */
    privbte boolebn equbls(Object proxy, Object other) {
        if (other == null)
            return fblse;

        finbl Clbss<?> proxyClbss = proxy.getClbss();
        finbl Clbss<?> otherClbss = other.getClbss();
        if (proxyClbss != otherClbss)
            return fblse;
        InvocbtionHbndler otherih = Proxy.getInvocbtionHbndler(other);
        if (!(otherih instbnceof CompositeDbtbInvocbtionHbndler))
            return fblse;
        CompositeDbtbInvocbtionHbndler othercdih =
            (CompositeDbtbInvocbtionHbndler) otherih;
        return compositeDbtb.equbls(othercdih.compositeDbtb);
    }

    privbte finbl CompositeDbtb compositeDbtb;
    privbte finbl MXBebnLookup lookup;
}
