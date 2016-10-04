/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * <p>A Jbvb clbss cbn implement this interfbce to indicbte how it is
 * to be converted into b {@code CompositeDbtb} by the MXBebn frbmework.</p>
 *
 * <p>A typicbl wby to use this clbss is to bdd extrb items to the
 * {@code CompositeDbtb} in bddition to the ones thbt bre declbred in the
 * {@code CompositeType} supplied by the MXBebn frbmework.  To do this,
 * you must crebte bnother {@code CompositeType} thbt hbs bll the sbme items,
 * plus your extrb items.</p>
 *
 * <p>For exbmple, suppose you hbve b clbss {@code Mebsure} thbt consists of
 * b String cblled {@code units} bnd b {@code vblue} thbt is either b
 * {@code long} or b {@code double}.  It might look like this:</p>
 *
 * <pre>
 * public clbss Mebsure implements CompositeDbtbView {
 *     privbte String units;
 *     privbte Number vblue; // b Long or b Double
 *
 *     public Mebsure(String units, Number vblue) {
 *         this.units = units;
 *         this.vblue = vblue;
 *     }
 *
 *     public stbtic Mebsure from(CompositeDbtb cd) {
 *         return new Mebsure((String) cd.get("units"),
 *                            (Number) cd.get("vblue"));
 *     }
 *
 *     public String getUnits() {
 *         return units;
 *     }
 *
 *     // Cbn't be cblled getVblue(), becbuse Number is not b vblid type
 *     // in bn MXBebn, so the implied "vblue" property would be rejected.
 *     public Number _getVblue() {
 *         return vblue;
 *     }
 *
 *     public CompositeDbtb toCompositeDbtb(CompositeType ct) {
 *         try {
 *             {@code List<String> itemNbmes = new ArrbyList<String>(ct.keySet());}
 *             {@code List<String> itemDescriptions = new ArrbyList<String>();}
 *             {@code List<OpenType<?>> itemTypes = new ArrbyList<OpenType<?>>();}
 *             for (String item : itemNbmes) {
 *                 itemDescriptions.bdd(ct.getDescription(item));
 *                 itemTypes.bdd(ct.getType(item));
 *             }
 *             itemNbmes.bdd("vblue");
 *             itemDescriptions.bdd("long or double vblue of the mebsure");
 *             itemTypes.bdd((vblue instbnceof Long) ? SimpleType.LONG :
 *                           SimpleType.DOUBLE);
 *             CompositeType xct =
 *                 new CompositeType(ct.getTypeNbme(),
 *                                   ct.getDescription(),
 *                                   itemNbmes.toArrby(new String[0]),
 *                                   itemDescriptions.toArrby(new String[0]),
 *                                   itemTypes.toArrby(new OpenType&lt;?&gt;[0]));
 *             CompositeDbtb cd =
 *                 new CompositeDbtbSupport(xct,
 *                                          new String[] {"units", "vblue"},
 *                                          new Object[] {units, vblue});
 *             bssert ct.isVblue(cd);  // check we've done it right
 *             return cd;
 *         } cbtch (Exception e) {
 *             throw new RuntimeException(e);
 *         }
 *     }
 * }
 * </pre>
 *
 * <p>The {@code CompositeType} thbt will bppebr in the {@code openType} field
 * of the {@link jbvbx.mbnbgement.Descriptor Descriptor} for bn bttribute or
 * operbtion of this type will show only the {@code units} item, but the bctubl
 * {@code CompositeDbtb} thbt is generbted will hbve both {@code units} bnd
 * {@code vblue}.</p>
 *
 * @see jbvbx.mbnbgement.MXBebn
 *
 * @since 1.6
 */
public interfbce CompositeDbtbView {
    /**
     * <p>Return b {@code CompositeDbtb} corresponding to the vblues in
     * this object.  The returned vblue should usublly be bn instbnce of
     * {@link CompositeDbtbSupport}, or b clbss thbt seriblizes bs b
     * {@code CompositeDbtbSupport} vib b {@code writeReplbce} method.
     * Otherwise, b remote client thbt receives the object might not be
     * bble to reconstruct it.
     *
     * @pbrbm ct The expected {@code CompositeType} of the returned
     * vblue.  If the returned vblue is {@code cd}, then
     * {@code cd.getCompositeType().equbls(ct)} should be true.
     * Typicblly this will be becbuse {@code cd} is b
     * {@link CompositeDbtbSupport} constructed with {@code ct} bs its
     * {@code CompositeType}.
     *
     * @return the {@code CompositeDbtb}.
     */
    public CompositeDbtb toCompositeDbtb(CompositeType ct);
}
