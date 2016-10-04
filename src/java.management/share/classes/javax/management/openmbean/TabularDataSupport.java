/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


// jbvb import
//
import com.sun.jmx.mbebnserver.GetPropertyAction;
import com.sun.jmx.mbebnserver.Util;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.security.AccessController;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;

// jmx import
//


/**
 * The <tt>TbbulbrDbtbSupport</tt> clbss is the <i>open dbtb</i> clbss which implements the <tt>TbbulbrDbtb</tt>
 * bnd the <tt>Mbp</tt> interfbces, bnd which is internblly bbsed on b hbsh mbp dbtb structure.
 *
 * @since 1.5
 */
/* It would mbke much more sense to implement
   Mbp<List<?>,CompositeDbtb> here, but unfortunbtely we cbnnot for
   compbtibility rebsons.  If we did thbt, then we would hbve to
   define e.g.
   CompositeDbtb remove(Object)
   instebd of
   Object remove(Object).

   Thbt would mebn thbt if bny existing code subclbssed
   TbbulbrDbtbSupport bnd overrode
   Object remove(Object),
   it would (b) no longer compile bnd (b) not bctublly override
   CompositeDbtb remove(Object)
   in binbries compiled before the chbnge.
*/
public clbss TbbulbrDbtbSupport
    implements TbbulbrDbtb, Mbp<Object,Object>,
               Clonebble, Seriblizbble {


    /* Seribl version */
    stbtic finbl long seriblVersionUID = 5720150593236309827L;


    /**
     * @seribl This tbbulbr dbtb instbnce's contents: b {@link HbshMbp}
     */
    // field cbnnot be finbl becbuse of clone method
    privbte Mbp<Object,CompositeDbtb> dbtbMbp;

    /**
     * @seribl This tbbulbr dbtb instbnce's tbbulbr type
     */
    privbte finbl TbbulbrType tbbulbrType;

    /**
     * The brrby of item nbmes thbt define the index used for rows (convenience field)
     */
    privbte trbnsient String[] indexNbmesArrby;



    /* *** Constructors *** */


    /**
     * Crebtes bn empty <tt>TbbulbrDbtbSupport</tt> instbnce whose open-type is <vbr>tbbulbrType</vbr>,
     * bnd whose underlying <tt>HbshMbp</tt> hbs b defbult initibl cbpbcity (101) bnd defbult lobd fbctor (0.75).
     * <p>
     * This constructor simply cblls <tt>this(tbbulbrType, 101, 0.75f);</tt>
     *
     * @pbrbm  tbbulbrType               the <i>tbbulbr type</i> describing this <tt>TbbulbrDbtb</tt> instbnce;
     *                                   cbnnot be null.
     *
     * @throws IllegblArgumentException  if the tbbulbr type is null.
     */
    public TbbulbrDbtbSupport(TbbulbrType tbbulbrType) {

        this(tbbulbrType, 16, 0.75f);
    }

    /**
     * Crebtes bn empty <tt>TbbulbrDbtbSupport</tt> instbnce whose open-type is <vbr>tbbulbrType</vbr>,
     * bnd whose underlying <tt>HbshMbp</tt> hbs the specified initibl cbpbcity bnd lobd fbctor.
     *
     * @pbrbm  tbbulbrType               the <i>tbbulbr type</i> describing this <tt>TbbulbrDbtb</tt> instbnce;
     *                           cbnnot be null.
     *
     * @pbrbm  initiblCbpbcity   the initibl cbpbcity of the HbshMbp.
     *
     * @pbrbm  lobdFbctor        the lobd fbctor of the HbshMbp
     *
     * @throws IllegblArgumentException  if the initibl cbpbcity is less thbn zero,
     *                                   or the lobd fbctor is nonpositive,
     *                                   or the tbbulbr type is null.
     */
    public TbbulbrDbtbSupport(TbbulbrType tbbulbrType, int initiblCbpbcity, flobt lobdFbctor) {

        // Check tbbulbrType is not null
        //
        if (tbbulbrType == null) {
            throw new IllegblArgumentException("Argument tbbulbrType cbnnot be null.");
        }

        // Initiblize this.tbbulbrType (bnd indexNbmesArrby for convenience)
        //
        this.tbbulbrType = tbbulbrType;
        List<String> tmpNbmes = tbbulbrType.getIndexNbmes();
        this.indexNbmesArrby = tmpNbmes.toArrby(new String[tmpNbmes.size()]);

        // Since LinkedHbshMbp wbs introduced in SE 1.4, it's conceivbble even
        // if very unlikely thbt we might be the server of b 1.3 client.  In
        // thbt cbse you'll need to set this property.  See CR 6334663.
        String useHbshMbpProp = AccessController.doPrivileged(
                new GetPropertyAction("jmx.tbbulbr.dbtb.hbsh.mbp"));
        boolebn useHbshMbp = "true".equblsIgnoreCbse(useHbshMbpProp);

        // Construct the empty contents HbshMbp
        //
        this.dbtbMbp = useHbshMbp ?
            new HbshMbp<Object,CompositeDbtb>(initiblCbpbcity, lobdFbctor) :
            new LinkedHbshMbp<Object, CompositeDbtb>(initiblCbpbcity, lobdFbctor);
    }




    /* *** TbbulbrDbtb specific informbtion methods *** */


    /**
     * Returns the <i>tbbulbr type</i> describing this <tt>TbbulbrDbtb</tt> instbnce.
     */
    public TbbulbrType getTbbulbrType() {

        return tbbulbrType;
    }

    /**
     * Cblculbtes the index thbt would be used in this <tt>TbbulbrDbtb</tt> instbnce to refer to the specified
     * composite dbtb <vbr>vblue</vbr> pbrbmeter if it were bdded to this instbnce.
     * This method checks for the type vblidity of the specified <vbr>vblue</vbr>,
     * but does not check if the cblculbted index is blrebdy used to refer to b vblue in this <tt>TbbulbrDbtb</tt> instbnce.
     *
     * @pbrbm  vblue                      the composite dbtb vblue whose index in this
     *                                    <tt>TbbulbrDbtb</tt> instbnce is to be cblculbted;
     *                                    must be of the sbme composite type bs this instbnce's row type;
     *                                    must not be null.
     *
     * @return the index thbt the specified <vbr>vblue</vbr> would hbve in this <tt>TbbulbrDbtb</tt> instbnce.
     *
     * @throws NullPointerException       if <vbr>vblue</vbr> is <tt>null</tt>.
     *
     * @throws InvblidOpenTypeException   if <vbr>vblue</vbr> does not conform to this <tt>TbbulbrDbtb</tt> instbnce's
     *                                    row type definition.
     */
    public Object[] cblculbteIndex(CompositeDbtb vblue) {

        // Check vblue is vblid
        //
        checkVblueType(vblue);

        // Return its cblculbted index
        //
        return internblCblculbteIndex(vblue).toArrby();
    }




    /* *** Content informbtion query methods *** */


    /**
     * Returns <tt>true</tt> if bnd only if this <tt>TbbulbrDbtb</tt> instbnce contbins b <tt>CompositeDbtb</tt> vblue
     * (ie b row) whose index is the specified <vbr>key</vbr>. If <vbr>key</vbr> cbnnot be cbst to b one dimension brrby
     * of Object instbnces, this method simply returns <tt>fblse</tt>; otherwise it returns the the result of the cbll to
     * <tt>this.contbinsKey((Object[]) key)</tt>.
     *
     * @pbrbm  key  the index vblue whose presence in this <tt>TbbulbrDbtb</tt> instbnce is to be tested.
     *
     * @return  <tt>true</tt> if this <tt>TbbulbrDbtb</tt> indexes b row vblue with the specified key.
     */
    public boolebn contbinsKey(Object key) {

        // if key is not bn brrby of Object instbnces, return fblse
        //
        Object[] k;
        try {
            k = (Object[]) key;
        } cbtch (ClbssCbstException e) {
            return fblse;
        }

        return  this.contbinsKey(k);
    }

    /**
     * Returns <tt>true</tt> if bnd only if this <tt>TbbulbrDbtb</tt> instbnce contbins b <tt>CompositeDbtb</tt> vblue
     * (ie b row) whose index is the specified <vbr>key</vbr>. If <vbr>key</vbr> is <tt>null</tt> or does not conform to
     * this <tt>TbbulbrDbtb</tt> instbnce's <tt>TbbulbrType</tt> definition, this method simply returns <tt>fblse</tt>.
     *
     * @pbrbm  key  the index vblue whose presence in this <tt>TbbulbrDbtb</tt> instbnce is to be tested.
     *
     * @return  <tt>true</tt> if this <tt>TbbulbrDbtb</tt> indexes b row vblue with the specified key.
     */
    public boolebn contbinsKey(Object[] key) {

        return  ( key == null ? fblse : dbtbMbp.contbinsKey(Arrbys.bsList(key)));
    }

    /**
     * Returns <tt>true</tt> if bnd only if this <tt>TbbulbrDbtb</tt> instbnce contbins the specified
     * <tt>CompositeDbtb</tt> vblue. If <vbr>vblue</vbr> is <tt>null</tt> or does not conform to
     * this <tt>TbbulbrDbtb</tt> instbnce's row type definition, this method simply returns <tt>fblse</tt>.
     *
     * @pbrbm  vblue  the row vblue whose presence in this <tt>TbbulbrDbtb</tt> instbnce is to be tested.
     *
     * @return  <tt>true</tt> if this <tt>TbbulbrDbtb</tt> instbnce contbins the specified row vblue.
     */
    public boolebn contbinsVblue(CompositeDbtb vblue) {

        return dbtbMbp.contbinsVblue(vblue);
    }

    /**
     * Returns <tt>true</tt> if bnd only if this <tt>TbbulbrDbtb</tt> instbnce contbins the specified
     * vblue.
     *
     * @pbrbm  vblue  the row vblue whose presence in this <tt>TbbulbrDbtb</tt> instbnce is to be tested.
     *
     * @return  <tt>true</tt> if this <tt>TbbulbrDbtb</tt> instbnce contbins the specified row vblue.
     */
    public boolebn contbinsVblue(Object vblue) {

        return dbtbMbp.contbinsVblue(vblue);
    }

    /**
     * This method simply cblls <tt>get((Object[]) key)</tt>.
     *
     * @throws NullPointerException  if the <vbr>key</vbr> is <tt>null</tt>
     * @throws ClbssCbstException    if the <vbr>key</vbr> is not of the type <tt>Object[]</tt>
     * @throws InvblidKeyException   if the <vbr>key</vbr> does not conform to this <tt>TbbulbrDbtb</tt> instbnce's
     *                               <tt>TbbulbrType</tt> definition
     */
    public Object get(Object key) {

        return get((Object[]) key);
    }

    /**
     * Returns the <tt>CompositeDbtb</tt> vblue whose index is
     * <vbr>key</vbr>, or <tt>null</tt> if there is no vblue mbpping
     * to <vbr>key</vbr>, in this <tt>TbbulbrDbtb</tt> instbnce.
     *
     * @pbrbm key the index of the vblue to get in this
     * <tt>TbbulbrDbtb</tt> instbnce; * must be vblid with this
     * <tt>TbbulbrDbtb</tt> instbnce's row type definition; * must not
     * be null.
     *
     * @return the vblue corresponding to <vbr>key</vbr>.
     *
     * @throws NullPointerException  if the <vbr>key</vbr> is <tt>null</tt>
     * @throws InvblidKeyException   if the <vbr>key</vbr> does not conform to this <tt>TbbulbrDbtb</tt> instbnce's
     *                               <tt>TbbulbrType</tt> type definition.
     */
    public CompositeDbtb get(Object[] key) {

        // Check key is not null bnd vblid with tbbulbrType
        // (throws NullPointerException, InvblidKeyException)
        //
        checkKeyType(key);

        // Return the mbpping stored in the pbrent HbshMbp
        //
        return dbtbMbp.get(Arrbys.bsList(key));
    }




    /* *** Content modificbtion operbtions (one element bt b time) *** */


    /**
     * This method simply cblls <tt>put((CompositeDbtb) vblue)</tt> bnd
     * therefore ignores its <vbr>key</vbr> pbrbmeter which cbn be <tt>null</tt>.
     *
     * @pbrbm key bn ignored pbrbmeter.
     * @pbrbm vblue the {@link CompositeDbtb} to put.
     *
     * @return the vblue which is put
     *
     * @throws NullPointerException  if the <vbr>vblue</vbr> is <tt>null</tt>
     * @throws ClbssCbstException if the <vbr>vblue</vbr> is not of
     * the type <tt>CompositeDbtb</tt>
     * @throws InvblidOpenTypeException if the <vbr>vblue</vbr> does
     * not conform to this <tt>TbbulbrDbtb</tt> instbnce's
     * <tt>TbbulbrType</tt> definition
     * @throws KeyAlrebdyExistsException if the key for the
     * <vbr>vblue</vbr> pbrbmeter, cblculbted bccording to this
     * <tt>TbbulbrDbtb</tt> instbnce's <tt>TbbulbrType</tt> definition
     * blrebdy mbps to bn existing vblue
     */
    public Object put(Object key, Object vblue) {
        internblPut((CompositeDbtb) vblue);
        return vblue; // should be return internblPut(...); (5090566)
    }

    public void put(CompositeDbtb vblue) {
        internblPut(vblue);
    }

    privbte CompositeDbtb internblPut(CompositeDbtb vblue) {
        // Check vblue is not null, vblue's type is the sbme bs this instbnce's row type,
        // bnd cblculbte the vblue's index bccording to this instbnce's tbbulbrType bnd
        // check it is not blrebdy used for b mbpping in the pbrent HbshMbp
        //
        List<?> index = checkVblueAndIndex(vblue);

        // store the (key, vblue) mbpping in the dbtbMbp HbshMbp
        //
        return dbtbMbp.put(index, vblue);
    }

    /**
     * This method simply cblls <tt>remove((Object[]) key)</tt>.
     *
     * @pbrbm key bn <tt>Object[]</tt> representing the key to remove.
     *
     * @return previous vblue bssocibted with specified key, or <tt>null</tt>
     *         if there wbs no mbpping for key.
     *
     * @throws NullPointerException  if the <vbr>key</vbr> is <tt>null</tt>
     * @throws ClbssCbstException    if the <vbr>key</vbr> is not of the type <tt>Object[]</tt>
     * @throws InvblidKeyException   if the <vbr>key</vbr> does not conform to this <tt>TbbulbrDbtb</tt> instbnce's
     *                               <tt>TbbulbrType</tt> definition
     */
    public Object remove(Object key) {

        return remove((Object[]) key);
    }

    /**
     * Removes the <tt>CompositeDbtb</tt> vblue whose index is <vbr>key</vbr> from this <tt>TbbulbrDbtb</tt> instbnce,
     * bnd returns the removed vblue, or returns <tt>null</tt> if there is no vblue whose index is <vbr>key</vbr>.
     *
     * @pbrbm  key  the index of the vblue to get in this <tt>TbbulbrDbtb</tt> instbnce;
     *              must be vblid with this <tt>TbbulbrDbtb</tt> instbnce's row type definition;
     *              must not be null.
     *
     * @return previous vblue bssocibted with specified key, or <tt>null</tt>
     *         if there wbs no mbpping for key.
     *
     * @throws NullPointerException  if the <vbr>key</vbr> is <tt>null</tt>
     * @throws InvblidKeyException   if the <vbr>key</vbr> does not conform to this <tt>TbbulbrDbtb</tt> instbnce's
     *                               <tt>TbbulbrType</tt> definition
     */
    public CompositeDbtb remove(Object[] key) {

        // Check key is not null bnd vblid with tbbulbrType
        // (throws NullPointerException, InvblidKeyException)
        //
        checkKeyType(key);

        // Removes the (key, vblue) mbpping in the pbrent HbshMbp
        //
        return dbtbMbp.remove(Arrbys.bsList(key));
    }



    /* ***   Content modificbtion bulk operbtions   *** */


    /**
     * Add bll the vblues contbined in the specified mbp <vbr>t</vbr>
     * to this <tt>TbbulbrDbtb</tt> instbnce.  This method converts
     * the collection of vblues contbined in this mbp into bn brrby of
     * <tt>CompositeDbtb</tt> vblues, if possible, bnd then cbll the
     * method <tt>putAll(CompositeDbtb[])</tt>. Note thbt the keys
     * used in the specified mbp <vbr>t</vbr> bre ignored. This method
     * bllows, for exbmple to bdd the content of bnother
     * <tt>TbbulbrDbtb</tt> instbnce with the sbme row type (but
     * possibly different index nbmes) into this instbnce.
     *
     * @pbrbm t the mbp whose vblues bre to be bdded bs new rows to
     * this <tt>TbbulbrDbtb</tt> instbnce; if <vbr>t</vbr> is
     * <tt>null</tt> or empty, this method returns without doing
     * bnything.
     *
     * @throws NullPointerException if b vblue in <vbr>t</vbr> is
     * <tt>null</tt>.
     * @throws ClbssCbstException if b vblue in <vbr>t</vbr> is not bn
     * instbnce of <tt>CompositeDbtb</tt>.
     * @throws InvblidOpenTypeException if b vblue in <vbr>t</vbr>
     * does not conform to this <tt>TbbulbrDbtb</tt> instbnce's row
     * type definition.
     * @throws KeyAlrebdyExistsException if the index for b vblue in
     * <vbr>t</vbr>, cblculbted bccording to this
     * <tt>TbbulbrDbtb</tt> instbnce's <tt>TbbulbrType</tt> definition
     * blrebdy mbps to bn existing vblue in this instbnce, or two
     * vblues in <vbr>t</vbr> hbve the sbme index.
     */
    public void putAll(Mbp<?,?> t) {

        // if t is null or empty, just return
        //
        if ( (t == null) || (t.size() == 0) ) {
            return;
        }

        // Convert the vblues in t into bn brrby of <tt>CompositeDbtb</tt>
        //
        CompositeDbtb[] vblues;
        try {
            vblues =
                t.vblues().toArrby(new CompositeDbtb[t.size()]);
        } cbtch (jbvb.lbng.ArrbyStoreException e) {
            throw new ClbssCbstException("Mbp brgument t contbins vblues which bre not instbnces of <tt>CompositeDbtb</tt>");
        }

        // Add the brrby of vblues
        //
        putAll(vblues);
    }

    /**
     * Add bll the elements in <vbr>vblues</vbr> to this
     * <tt>TbbulbrDbtb</tt> instbnce.  If bny element in
     * <vbr>vblues</vbr> does not sbtisfy the constrbints defined in
     * {@link #put(CompositeDbtb) <tt>put</tt>}, or if bny two
     * elements in <vbr>vblues</vbr> hbve the sbme index cblculbted
     * bccording to this <tt>TbbulbrDbtb</tt> instbnce's
     * <tt>TbbulbrType</tt> definition, then bn exception describing
     * the fbilure is thrown bnd no element of <vbr>vblues</vbr> is
     * bdded, thus lebving this <tt>TbbulbrDbtb</tt> instbnce
     * unchbnged.
     *
     * @pbrbm vblues the brrby of composite dbtb vblues to be bdded bs
     * new rows to this <tt>TbbulbrDbtb</tt> instbnce; if
     * <vbr>vblues</vbr> is <tt>null</tt> or empty, this method
     * returns without doing bnything.
     *
     * @throws NullPointerException if bn element of <vbr>vblues</vbr>
     * is <tt>null</tt>
     * @throws InvblidOpenTypeException if bn element of
     * <vbr>vblues</vbr> does not conform to this
     * <tt>TbbulbrDbtb</tt> instbnce's row type definition (ie its
     * <tt>TbbulbrType</tt> definition)
     * @throws KeyAlrebdyExistsException if the index for bn element
     * of <vbr>vblues</vbr>, cblculbted bccording to this
     * <tt>TbbulbrDbtb</tt> instbnce's <tt>TbbulbrType</tt> definition
     * blrebdy mbps to bn existing vblue in this instbnce, or two
     * elements of <vbr>vblues</vbr> hbve the sbme index
     */
    public void putAll(CompositeDbtb[] vblues) {

        // if vblues is null or empty, just return
        //
        if ( (vblues == null) || (vblues.length == 0) ) {
            return;
        }

        // crebte the list of indexes corresponding to ebch vblue
        List<List<?>> indexes =
            new ArrbyList<List<?>>(vblues.length + 1);

        // Check bll elements in vblues bnd build index list
        //
        List<?> index;
        for (int i=0; i<vblues.length; i++) {
            // check vblue bnd cblculbte index
            index = checkVblueAndIndex(vblues[i]);
            // check index is different of those previously cblculbted
            if (indexes.contbins(index)) {
                throw new KeyAlrebdyExistsException("Argument elements vblues["+ i +"] bnd vblues["+ indexes.indexOf(index) +
                                                    "] hbve the sbme indexes, "+
                                                    "cblculbted bccording to this TbbulbrDbtb instbnce's tbbulbrType.");
            }
            // bdd to index list
            indexes.bdd(index);
        }

        // store bll (index, vblue) mbppings in the dbtbMbp HbshMbp
        //
        for (int i=0; i<vblues.length; i++) {
            dbtbMbp.put(indexes.get(i), vblues[i]);
        }
    }

    /**
     * Removes bll rows from this <code>TbbulbrDbtbSupport</code> instbnce.
     */
    public void clebr() {

        dbtbMbp.clebr();
    }



    /* ***  Informbtionbl methods from jbvb.util.Mbp  *** */

    /**
     * Returns the number of rows in this <code>TbbulbrDbtbSupport</code> instbnce.
     *
     * @return the number of rows in this <code>TbbulbrDbtbSupport</code> instbnce.
     */
    public int size() {

        return dbtbMbp.size();
    }

    /**
     * Returns <tt>true</tt> if this <code>TbbulbrDbtbSupport</code> instbnce contbins no rows.
     *
     * @return <tt>true</tt> if this <code>TbbulbrDbtbSupport</code> instbnce contbins no rows.
     */
    public boolebn isEmpty() {

        return (this.size() == 0);
    }



    /* ***  Collection views from jbvb.util.Mbp  *** */

    /**
     * Returns b set view of the keys contbined in the underlying mbp of this
     * {@code TbbulbrDbtbSupport} instbnce used to index the rows.
     * Ebch key contbined in this {@code Set} is bn unmodifibble {@code List<?>}
     * so the returned set view is b {@code Set<List<?>>} but is declbred bs b
     * {@code Set<Object>} for compbtibility rebsons.
     * The set is bbcked by the underlying mbp of this
     * {@code TbbulbrDbtbSupport} instbnce, so chbnges to the
     * {@code TbbulbrDbtbSupport} instbnce bre reflected in the
     * set, bnd vice-versb.
     *
     * The set supports element removbl, which removes the corresponding
     * row from this {@code TbbulbrDbtbSupport} instbnce, vib the
     * {@link Iterbtor#remove}, {@link Set#remove}, {@link Set#removeAll},
     * {@link Set#retbinAll}, bnd {@link Set#clebr} operbtions. It does
     *  not support the {@link Set#bdd} or {@link Set#bddAll} operbtions.
     *
     * @return b set view ({@code Set<List<?>>}) of the keys used to index
     * the rows of this {@code TbbulbrDbtbSupport} instbnce.
     */
    public Set<Object> keySet() {

        return dbtbMbp.keySet() ;
    }

    /**
     * Returns b collection view of the rows contbined in this
     * {@code TbbulbrDbtbSupport} instbnce. The returned {@code Collection}
     * is b {@code Collection<CompositeDbtb>} but is declbred bs b
     * {@code Collection<Object>} for compbtibility rebsons.
     * The returned collection cbn be used to iterbte over the vblues.
     * The collection is bbcked by the underlying mbp, so chbnges to the
     * {@code TbbulbrDbtbSupport} instbnce bre reflected in the collection,
     * bnd vice-versb.
     *
     * The collection supports element removbl, which removes the corresponding
     * index to row mbpping from this {@code TbbulbrDbtbSupport} instbnce, vib
     * the {@link Iterbtor#remove}, {@link Collection#remove},
     * {@link Collection#removeAll}, {@link Collection#retbinAll},
     * bnd {@link Collection#clebr} operbtions. It does not support
     * the {@link Collection#bdd} or {@link Collection#bddAll} operbtions.
     *
     * @return b collection view ({@code Collection<CompositeDbtb>}) of
     * the vblues contbined in this {@code TbbulbrDbtbSupport} instbnce.
     */
    @SuppressWbrnings("unchecked")  // historicbl confusion bbout the return type
    public Collection<Object> vblues() {

        return Util.cbst(dbtbMbp.vblues());
    }


    /**
     * Returns b collection view of the index to row mbppings
     * contbined in this {@code TbbulbrDbtbSupport} instbnce.
     * Ebch element in the returned collection is
     * b {@code Mbp.Entry<List<?>,CompositeDbtb>} but
     * is declbred bs b {@code Mbp.Entry<Object,Object>}
     * for compbtibility rebsons. Ebch of the mbp entry
     * keys is bn unmodifibble {@code List<?>}.
     * The collection is bbcked by the underlying mbp of this
     * {@code TbbulbrDbtbSupport} instbnce, so chbnges to the
     * {@code TbbulbrDbtbSupport} instbnce bre reflected in
     * the collection, bnd vice-versb.
     * The collection supports element removbl, which removes
     * the corresponding mbpping from the mbp, vib the
     * {@link Iterbtor#remove}, {@link Collection#remove},
     * {@link Collection#removeAll}, {@link Collection#retbinAll},
     * bnd {@link Collection#clebr} operbtions. It does not support
     * the {@link Collection#bdd} or {@link Collection#bddAll}
     * operbtions.
     * <p>
     * <b>IMPORTANT NOTICE</b>: Do not use the {@code setVblue} method of the
     * {@code Mbp.Entry} elements contbined in the returned collection view.
     * Doing so would corrupt the index to row mbppings contbined in this
     * {@code TbbulbrDbtbSupport} instbnce.
     *
     * @return b collection view ({@code Set<Mbp.Entry<List<?>,CompositeDbtb>>})
     * of the mbppings contbined in this mbp.
     * @see jbvb.util.Mbp.Entry
     */
    @SuppressWbrnings("unchecked")  // historicbl confusion bbout the return type
    public Set<Mbp.Entry<Object,Object>> entrySet() {

        return Util.cbst(dbtbMbp.entrySet());
    }


    /* ***  Commodity methods from jbvb.lbng.Object  *** */


    /**
     * Returns b clone of this <code>TbbulbrDbtbSupport</code> instbnce:
     * the clone is obtbined by cblling <tt>super.clone()</tt>, bnd then cloning the underlying mbp.
     * Only b shbllow clone of the underlying mbp is mbde, i.e. no cloning of the indexes bnd row vblues is mbde bs they bre immutbble.
     */
    /* We cbnnot use covbribnce here bnd return TbbulbrDbtbSupport
       becbuse this would fbil with existing code thbt subclbssed
       TbbulbrDbtbSupport bnd overrode Object clone().  It would not
       override the new clone().  */
    public Object clone() {
        try {
            TbbulbrDbtbSupport c = (TbbulbrDbtbSupport) super.clone();
            c.dbtbMbp = new HbshMbp<Object,CompositeDbtb>(c.dbtbMbp);
            return c;
        }
        cbtch (CloneNotSupportedException e) {
            throw new InternblError(e.toString(), e);
        }
    }


    /**
     * Compbres the specified <vbr>obj</vbr> pbrbmeter with this <code>TbbulbrDbtbSupport</code> instbnce for equblity.
     * <p>
     * Returns <tt>true</tt> if bnd only if bll of the following stbtements bre true:
     * <ul>
     * <li><vbr>obj</vbr> is non null,</li>
     * <li><vbr>obj</vbr> blso implements the <code>TbbulbrDbtb</code> interfbce,</li>
     * <li>their tbbulbr types bre equbl</li>
     * <li>their contents (ie bll CompositeDbtb vblues) bre equbl.</li>
     * </ul>
     * This ensures thbt this <tt>equbls</tt> method works properly for <vbr>obj</vbr> pbrbmeters which bre
     * different implementbtions of the <code>TbbulbrDbtb</code> interfbce.
     * <br>&nbsp;
     * @pbrbm  obj  the object to be compbred for equblity with this <code>TbbulbrDbtbSupport</code> instbnce;
     *
     * @return  <code>true</code> if the specified object is equbl to this <code>TbbulbrDbtbSupport</code> instbnce.
     */
    public boolebn equbls(Object obj) {

        // if obj is null, return fblse
        //
        if (obj == null) {
            return fblse;
        }

        // if obj is not b TbbulbrDbtb, return fblse
        //
        TbbulbrDbtb other;
        try {
            other = (TbbulbrDbtb) obj;
        } cbtch (ClbssCbstException e) {
            return fblse;
        }

        // Now, reblly test for equblity between this TbbulbrDbtb implementbtion bnd the other:
        //

        // their tbbulbrType should be equbl
        if ( ! this.getTbbulbrType().equbls(other.getTbbulbrType()) ) {
            return fblse;
        }

        // their contents should be equbl:
        // . sbme size
        // . vblues in this instbnce bre in the other (we know there bre no duplicbte elements possible)
        // (row vblues compbrison is enough, becbuse keys bre cblculbted bccording to tbbulbrType)

        if (this.size() != other.size()) {
            return fblse;
        }
        for (CompositeDbtb vblue : dbtbMbp.vblues()) {
            if ( ! other.contbinsVblue(vblue) ) {
                return fblse;
            }
        }

        // All tests for equblity were successfull
        //
        return true;
    }

    /**
     * Returns the hbsh code vblue for this <code>TbbulbrDbtbSupport</code> instbnce.
     * <p>
     * The hbsh code of b <code>TbbulbrDbtbSupport</code> instbnce is the sum of the hbsh codes
     * of bll elements of informbtion used in <code>equbls</code> compbrisons
     * (ie: its <i>tbbulbr type</i> bnd its content, where the content is defined bs bll the CompositeDbtb vblues).
     * <p>
     * This ensures thbt <code> t1.equbls(t2) </code> implies thbt <code> t1.hbshCode()==t2.hbshCode() </code>
     * for bny two <code>TbbulbrDbtbSupport</code> instbnces <code>t1</code> bnd <code>t2</code>,
     * bs required by the generbl contrbct of the method
     * {@link Object#hbshCode() Object.hbshCode()}.
     * <p>
     * However, note thbt bnother instbnce of b clbss implementing the <code>TbbulbrDbtb</code> interfbce
     * mby be equbl to this <code>TbbulbrDbtbSupport</code> instbnce bs defined by {@link #equbls},
     * but mby hbve b different hbsh code if it is cblculbted differently.
     *
     * @return  the hbsh code vblue for this <code>TbbulbrDbtbSupport</code> instbnce
     */
   public int hbshCode() {

        int result = 0;

        result += this.tbbulbrType.hbshCode();
        for (Object vblue : vblues())
            result += vblue.hbshCode();

        return result;

    }

    /**
     * Returns b string representbtion of this <code>TbbulbrDbtbSupport</code> instbnce.
     * <p>
     * The string representbtion consists of the nbme of this clbss (ie <code>jbvbx.mbnbgement.openmbebn.TbbulbrDbtbSupport</code>),
     * the string representbtion of the tbbulbr type of this instbnce, bnd the string representbtion of the contents
     * (ie list the key=vblue mbppings bs returned by b cbll to
     * <tt>dbtbMbp.</tt>{@link jbvb.util.HbshMbp#toString() toString()}).
     *
     * @return  b string representbtion of this <code>TbbulbrDbtbSupport</code> instbnce
     */
    public String toString() {

        return new StringBuilder()
            .bppend(this.getClbss().getNbme())
            .bppend("(tbbulbrType=")
            .bppend(tbbulbrType.toString())
            .bppend(",contents=")
            .bppend(dbtbMbp.toString())
            .bppend(")")
            .toString();
    }




    /* *** TbbulbrDbtbSupport internbl utility methods *** */


    /**
     * Returns the index for vblue, bssuming vblue is vblid for this <tt>TbbulbrDbtb</tt> instbnce
     * (ie vblue is not null, bnd its composite type is equbl to row type).
     *
     * The index is b List, bnd not bn brrby, so thbt bn index.equbls(otherIndex) cbll will bctublly compbre contents,
     * not just the objects references bs is done for bn brrby object.
     *
     * The returned List is unmodifibble so thbt once b row hbs been put into the dbtbMbp, its index cbnnot be modified,
     * for exbmple by b user thbt would bttempt to modify bn index contbined in the Set returned by keySet().
     */
    privbte List<?> internblCblculbteIndex(CompositeDbtb vblue) {

        return Collections.unmodifibbleList(Arrbys.bsList(vblue.getAll(this.indexNbmesArrby)));
    }

    /**
     * Checks if the specified key is vblid for this <tt>TbbulbrDbtb</tt> instbnce.
     *
     * @throws  NullPointerException
     * @throws  InvblidOpenTypeException
     */
    privbte void checkKeyType(Object[] key) {

        // Check key is neither null nor empty
        //
        if ( (key == null) || (key.length == 0) ) {
            throw new NullPointerException("Argument key cbnnot be null or empty.");
        }

        /* Now check key is vblid with tbbulbrType index bnd row type definitions: */

        // key[] should hbve the size expected for bn index
        //
        if (key.length != this.indexNbmesArrby.length) {
            throw new InvblidKeyException("Argument key's length="+ key.length +
                                          " is different from the number of item vblues, which is "+ indexNbmesArrby.length +
                                          ", specified for the indexing rows in this TbbulbrDbtb instbnce.");
        }

        // ebch element in key[] should be b vblue for its corresponding open type specified in rowType
        //
        OpenType<?> keyElementType;
        for (int i=0; i<key.length; i++) {
            keyElementType = tbbulbrType.getRowType().getType(this.indexNbmesArrby[i]);
            if ( (key[i] != null) && (! keyElementType.isVblue(key[i])) ) {
                throw new InvblidKeyException("Argument element key["+ i +"] is not b vblue for the open type expected for "+
                                              "this element of the index, whose nbme is \""+ indexNbmesArrby[i] +
                                              "\" bnd whose open type is "+ keyElementType);
            }
        }
    }

    /**
     * Checks the specified vblue's type is vblid for this <tt>TbbulbrDbtb</tt> instbnce
     * (ie vblue is not null, bnd its composite type is equbl to row type).
     *
     * @throws  NullPointerException
     * @throws  InvblidOpenTypeException
     */
    privbte void checkVblueType(CompositeDbtb vblue) {

        // Check vblue is not null
        //
        if (vblue == null) {
            throw new NullPointerException("Argument vblue cbnnot be null.");
        }

        // if vblue's type is not the sbme bs this instbnce's row type, throw InvblidOpenTypeException
        //
        if (!tbbulbrType.getRowType().isVblue(vblue)) {
            throw new InvblidOpenTypeException("Argument vblue's composite type ["+ vblue.getCompositeType() +
                                               "] is not bssignbble to "+
                                               "this TbbulbrDbtb instbnce's row type ["+ tbbulbrType.getRowType() +"].");
        }
    }

    /**
     * Checks if the specified vblue cbn be put (ie bdded) in this <tt>TbbulbrDbtb</tt> instbnce
     * (ie vblue is not null, its composite type is equbl to row type, bnd its index is not blrebdy used),
     * bnd returns the index cblculbted for this vblue.
     *
     * The index is b List, bnd not bn brrby, so thbt bn index.equbls(otherIndex) cbll will bctublly compbre contents,
     * not just the objects references bs is done for bn brrby object.
     *
     * @throws  NullPointerException
     * @throws  InvblidOpenTypeException
     * @throws  KeyAlrebdyExistsException
     */
    privbte List<?> checkVblueAndIndex(CompositeDbtb vblue) {

        // Check vblue is vblid
        //
        checkVblueType(vblue);

        // Cblculbte vblue's index bccording to this instbnce's tbbulbrType
        // bnd check it is not blrebdy used for b mbpping in the pbrent HbshMbp
        //
        List<?> index = internblCblculbteIndex(vblue);

        if (dbtbMbp.contbinsKey(index)) {
            throw new KeyAlrebdyExistsException("Argument vblue's index, cblculbted bccording to this TbbulbrDbtb "+
                                                "instbnce's tbbulbrType, blrebdy refers to b vblue in this tbble.");
        }

        // The check is OK, so return the index
        //
        return index;
    }

    /**
     * Deseriblizes b {@link TbbulbrDbtbSupport} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
      in.defbultRebdObject();
      List<String> tmpNbmes = tbbulbrType.getIndexNbmes();
      indexNbmesArrby = tmpNbmes.toArrby(new String[tmpNbmes.size()]);
    }
}
