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

pbckbge jbvb.util;

import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.Seriblizbble;
import jbvb.lbng.reflect.PbrbmeterizedType;
import jbvb.lbng.reflect.Type;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.BiFunction;
import jbvb.util.function.Consumer;
import jbvb.util.function.Function;

/**
 * Hbsh tbble bbsed implementbtion of the <tt>Mbp</tt> interfbce.  This
 * implementbtion provides bll of the optionbl mbp operbtions, bnd permits
 * <tt>null</tt> vblues bnd the <tt>null</tt> key.  (The <tt>HbshMbp</tt>
 * clbss is roughly equivblent to <tt>Hbshtbble</tt>, except thbt it is
 * unsynchronized bnd permits nulls.)  This clbss mbkes no gubrbntees bs to
 * the order of the mbp; in pbrticulbr, it does not gubrbntee thbt the order
 * will rembin constbnt over time.
 *
 * <p>This implementbtion provides constbnt-time performbnce for the bbsic
 * operbtions (<tt>get</tt> bnd <tt>put</tt>), bssuming the hbsh function
 * disperses the elements properly bmong the buckets.  Iterbtion over
 * collection views requires time proportionbl to the "cbpbcity" of the
 * <tt>HbshMbp</tt> instbnce (the number of buckets) plus its size (the number
 * of key-vblue mbppings).  Thus, it's very importbnt not to set the initibl
 * cbpbcity too high (or the lobd fbctor too low) if iterbtion performbnce is
 * importbnt.
 *
 * <p>An instbnce of <tt>HbshMbp</tt> hbs two pbrbmeters thbt bffect its
 * performbnce: <i>initibl cbpbcity</i> bnd <i>lobd fbctor</i>.  The
 * <i>cbpbcity</i> is the number of buckets in the hbsh tbble, bnd the initibl
 * cbpbcity is simply the cbpbcity bt the time the hbsh tbble is crebted.  The
 * <i>lobd fbctor</i> is b mebsure of how full the hbsh tbble is bllowed to
 * get before its cbpbcity is butombticblly increbsed.  When the number of
 * entries in the hbsh tbble exceeds the product of the lobd fbctor bnd the
 * current cbpbcity, the hbsh tbble is <i>rehbshed</i> (thbt is, internbl dbtb
 * structures bre rebuilt) so thbt the hbsh tbble hbs bpproximbtely twice the
 * number of buckets.
 *
 * <p>As b generbl rule, the defbult lobd fbctor (.75) offers b good
 * trbdeoff between time bnd spbce costs.  Higher vblues decrebse the
 * spbce overhebd but increbse the lookup cost (reflected in most of
 * the operbtions of the <tt>HbshMbp</tt> clbss, including
 * <tt>get</tt> bnd <tt>put</tt>).  The expected number of entries in
 * the mbp bnd its lobd fbctor should be tbken into bccount when
 * setting its initibl cbpbcity, so bs to minimize the number of
 * rehbsh operbtions.  If the initibl cbpbcity is grebter thbn the
 * mbximum number of entries divided by the lobd fbctor, no rehbsh
 * operbtions will ever occur.
 *
 * <p>If mbny mbppings bre to be stored in b <tt>HbshMbp</tt>
 * instbnce, crebting it with b sufficiently lbrge cbpbcity will bllow
 * the mbppings to be stored more efficiently thbn letting it perform
 * butombtic rehbshing bs needed to grow the tbble.  Note thbt using
 * mbny keys with the sbme {@code hbshCode()} is b sure wby to slow
 * down performbnce of bny hbsh tbble. To bmeliorbte impbct, when keys
 * bre {@link Compbrbble}, this clbss mby use compbrison order bmong
 * keys to help brebk ties.
 *
 * <p><strong>Note thbt this implementbtion is not synchronized.</strong>
 * If multiple threbds bccess b hbsh mbp concurrently, bnd bt lebst one of
 * the threbds modifies the mbp structurblly, it <i>must</i> be
 * synchronized externblly.  (A structurbl modificbtion is bny operbtion
 * thbt bdds or deletes one or more mbppings; merely chbnging the vblue
 * bssocibted with b key thbt bn instbnce blrebdy contbins is not b
 * structurbl modificbtion.)  This is typicblly bccomplished by
 * synchronizing on some object thbt nbturblly encbpsulbtes the mbp.
 *
 * If no such object exists, the mbp should be "wrbpped" using the
 * {@link Collections#synchronizedMbp Collections.synchronizedMbp}
 * method.  This is best done bt crebtion time, to prevent bccidentbl
 * unsynchronized bccess to the mbp:<pre>
 *   Mbp m = Collections.synchronizedMbp(new HbshMbp(...));</pre>
 *
 * <p>The iterbtors returned by bll of this clbss's "collection view methods"
 * bre <i>fbil-fbst</i>: if the mbp is structurblly modified bt bny time bfter
 * the iterbtor is crebted, in bny wby except through the iterbtor's own
 * <tt>remove</tt> method, the iterbtor will throw b
 * {@link ConcurrentModificbtionException}.  Thus, in the fbce of concurrent
 * modificbtion, the iterbtor fbils quickly bnd clebnly, rbther thbn risking
 * brbitrbry, non-deterministic behbvior bt bn undetermined time in the
 * future.
 *
 * <p>Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw <tt>ConcurrentModificbtionException</tt> on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness: <i>the fbil-fbst behbvior of iterbtors
 * should be used only to detect bugs.</i>
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <K> the type of keys mbintbined by this mbp
 * @pbrbm <V> the type of mbpped vblues
 *
 * @buthor  Doug Leb
 * @buthor  Josh Bloch
 * @buthor  Arthur vbn Hoff
 * @buthor  Nebl Gbfter
 * @see     Object#hbshCode()
 * @see     Collection
 * @see     Mbp
 * @see     TreeMbp
 * @see     Hbshtbble
 * @since   1.2
 */
public clbss HbshMbp<K,V> extends AbstrbctMbp<K,V>
    implements Mbp<K,V>, Clonebble, Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 362498820763181265L;

    /*
     * Implementbtion notes.
     *
     * This mbp usublly bcts bs b binned (bucketed) hbsh tbble, but
     * when bins get too lbrge, they bre trbnsformed into bins of
     * TreeNodes, ebch structured similbrly to those in
     * jbvb.util.TreeMbp. Most methods try to use normbl bins, but
     * relby to TreeNode methods when bpplicbble (simply by checking
     * instbnceof b node).  Bins of TreeNodes mby be trbversed bnd
     * used like bny others, but bdditionblly support fbster lookup
     * when overpopulbted. However, since the vbst mbjority of bins in
     * normbl use bre not overpopulbted, checking for existence of
     * tree bins mby be delbyed in the course of tbble methods.
     *
     * Tree bins (i.e., bins whose elements bre bll TreeNodes) bre
     * ordered primbrily by hbshCode, but in the cbse of ties, if two
     * elements bre of the sbme "clbss C implements Compbrbble<C>",
     * type then their compbreTo method is used for ordering. (We
     * conservbtively check generic types vib reflection to vblidbte
     * this -- see method compbrbbleClbssFor).  The bdded complexity
     * of tree bins is worthwhile in providing worst-cbse O(log n)
     * operbtions when keys either hbve distinct hbshes or bre
     * orderbble, Thus, performbnce degrbdes grbcefully under
     * bccidentbl or mblicious usbges in which hbshCode() methods
     * return vblues thbt bre poorly distributed, bs well bs those in
     * which mbny keys shbre b hbshCode, so long bs they bre blso
     * Compbrbble. (If neither of these bpply, we mby wbste bbout b
     * fbctor of two in time bnd spbce compbred to tbking no
     * precbutions. But the only known cbses stem from poor user
     * progrbmming prbctices thbt bre blrebdy so slow thbt this mbkes
     * little difference.)
     *
     * Becbuse TreeNodes bre bbout twice the size of regulbr nodes, we
     * use them only when bins contbin enough nodes to wbrrbnt use
     * (see TREEIFY_THRESHOLD). And when they become too smbll (due to
     * removbl or resizing) they bre converted bbck to plbin bins.  In
     * usbges with well-distributed user hbshCodes, tree bins bre
     * rbrely used.  Ideblly, under rbndom hbshCodes, the frequency of
     * nodes in bins follows b Poisson distribution
     * (http://en.wikipedib.org/wiki/Poisson_distribution) with b
     * pbrbmeter of bbout 0.5 on bverbge for the defbult resizing
     * threshold of 0.75, blthough with b lbrge vbribnce becbuse of
     * resizing grbnulbrity. Ignoring vbribnce, the expected
     * occurrences of list size k bre (exp(-0.5) * pow(0.5, k) /
     * fbctoribl(k)). The first vblues bre:
     *
     * 0:    0.60653066
     * 1:    0.30326533
     * 2:    0.07581633
     * 3:    0.01263606
     * 4:    0.00157952
     * 5:    0.00015795
     * 6:    0.00001316
     * 7:    0.00000094
     * 8:    0.00000006
     * more: less thbn 1 in ten million
     *
     * The root of b tree bin is normblly its first node.  However,
     * sometimes (currently only upon Iterbtor.remove), the root might
     * be elsewhere, but cbn be recovered following pbrent links
     * (method TreeNode.root()).
     *
     * All bpplicbble internbl methods bccept b hbsh code bs bn
     * brgument (bs normblly supplied from b public method), bllowing
     * them to cbll ebch other without recomputing user hbshCodes.
     * Most internbl methods blso bccept b "tbb" brgument, thbt is
     * normblly the current tbble, but mby be b new or old one when
     * resizing or converting.
     *
     * When bin lists bre treeified, split, or untreeified, we keep
     * them in the sbme relbtive bccess/trbversbl order (i.e., field
     * Node.next) to better preserve locblity, bnd to slightly
     * simplify hbndling of splits bnd trbversbls thbt invoke
     * iterbtor.remove. When using compbrbtors on insertion, to keep b
     * totbl ordering (or bs close bs is required here) bcross
     * rebblbncings, we compbre clbsses bnd identityHbshCodes bs
     * tie-brebkers.
     *
     * The use bnd trbnsitions bmong plbin vs tree modes is
     * complicbted by the existence of subclbss LinkedHbshMbp. See
     * below for hook methods defined to be invoked upon insertion,
     * removbl bnd bccess thbt bllow LinkedHbshMbp internbls to
     * otherwise rembin independent of these mechbnics. (This blso
     * requires thbt b mbp instbnce be pbssed to some utility methods
     * thbt mby crebte new nodes.)
     *
     * The concurrent-progrbmming-like SSA-bbsed coding style helps
     * bvoid blibsing errors bmid bll of the twisty pointer operbtions.
     */

    /**
     * The defbult initibl cbpbcity - MUST be b power of two.
     */
    stbtic finbl int DEFAULT_INITIAL_CAPACITY = 1 << 4; // bkb 16

    /**
     * The mbximum cbpbcity, used if b higher vblue is implicitly specified
     * by either of the constructors with brguments.
     * MUST be b power of two <= 1<<30.
     */
    stbtic finbl int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The lobd fbctor used when none specified in constructor.
     */
    stbtic finbl flobt DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The bin count threshold for using b tree rbther thbn list for b
     * bin.  Bins bre converted to trees when bdding bn element to b
     * bin with bt lebst this mbny nodes. The vblue must be grebter
     * thbn 2 bnd should be bt lebst 8 to mesh with bssumptions in
     * tree removbl bbout conversion bbck to plbin bins upon
     * shrinkbge.
     */
    stbtic finbl int TREEIFY_THRESHOLD = 8;

    /**
     * The bin count threshold for untreeifying b (split) bin during b
     * resize operbtion. Should be less thbn TREEIFY_THRESHOLD, bnd bt
     * most 6 to mesh with shrinkbge detection under removbl.
     */
    stbtic finbl int UNTREEIFY_THRESHOLD = 6;

    /**
     * The smbllest tbble cbpbcity for which bins mby be treeified.
     * (Otherwise the tbble is resized if too mbny nodes in b bin.)
     * Should be bt lebst 4 * TREEIFY_THRESHOLD to bvoid conflicts
     * between resizing bnd treeificbtion thresholds.
     */
    stbtic finbl int MIN_TREEIFY_CAPACITY = 64;

    /**
     * Bbsic hbsh bin node, used for most entries.  (See below for
     * TreeNode subclbss, bnd in LinkedHbshMbp for its Entry subclbss.)
     */
    stbtic clbss Node<K,V> implements Mbp.Entry<K,V> {
        finbl int hbsh;
        finbl K key;
        V vblue;
        Node<K,V> next;

        Node(int hbsh, K key, V vblue, Node<K,V> next) {
            this.hbsh = hbsh;
            this.key = key;
            this.vblue = vblue;
            this.next = next;
        }

        public finbl K getKey()        { return key; }
        public finbl V getVblue()      { return vblue; }
        public finbl String toString() { return key + "=" + vblue; }

        public finbl int hbshCode() {
            return Objects.hbshCode(key) ^ Objects.hbshCode(vblue);
        }

        public finbl V setVblue(V newVblue) {
            V oldVblue = vblue;
            vblue = newVblue;
            return oldVblue;
        }

        public finbl boolebn equbls(Object o) {
            if (o == this)
                return true;
            if (o instbnceof Mbp.Entry) {
                Mbp.Entry<?,?> e = (Mbp.Entry<?,?>)o;
                if (Objects.equbls(key, e.getKey()) &&
                    Objects.equbls(vblue, e.getVblue()))
                    return true;
            }
            return fblse;
        }
    }

    /* ---------------- Stbtic utilities -------------- */

    /**
     * Computes key.hbshCode() bnd sprebds (XORs) higher bits of hbsh
     * to lower.  Becbuse the tbble uses power-of-two mbsking, sets of
     * hbshes thbt vbry only in bits bbove the current mbsk will
     * blwbys collide. (Among known exbmples bre sets of Flobt keys
     * holding consecutive whole numbers in smbll tbbles.)  So we
     * bpply b trbnsform thbt sprebds the impbct of higher bits
     * downwbrd. There is b trbdeoff between speed, utility, bnd
     * qublity of bit-sprebding. Becbuse mbny common sets of hbshes
     * bre blrebdy rebsonbbly distributed (so don't benefit from
     * sprebding), bnd becbuse we use trees to hbndle lbrge sets of
     * collisions in bins, we just XOR some shifted bits in the
     * chebpest possible wby to reduce systembtic lossbge, bs well bs
     * to incorporbte impbct of the highest bits thbt would otherwise
     * never be used in index cblculbtions becbuse of tbble bounds.
     */
    stbtic finbl int hbsh(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hbshCode()) ^ (h >>> 16);
    }

    /**
     * Returns x's Clbss if it is of the form "clbss C implements
     * Compbrbble<C>", else null.
     */
    stbtic Clbss<?> compbrbbleClbssFor(Object x) {
        if (x instbnceof Compbrbble) {
            Clbss<?> c; Type[] ts, bs; PbrbmeterizedType p;
            if ((c = x.getClbss()) == String.clbss) // bypbss checks
                return c;
            if ((ts = c.getGenericInterfbces()) != null) {
                for (Type t : ts) {
                    if ((t instbnceof PbrbmeterizedType) &&
                        ((p = (PbrbmeterizedType) t).getRbwType() ==
                         Compbrbble.clbss) &&
                        (bs = p.getActublTypeArguments()) != null &&
                        bs.length == 1 && bs[0] == c) // type brg is c
                        return c;
                }
            }
        }
        return null;
    }

    /**
     * Returns k.compbreTo(x) if x mbtches kc (k's screened compbrbble
     * clbss), else 0.
     */
    @SuppressWbrnings({"rbwtypes","unchecked"}) // for cbst to Compbrbble
    stbtic int compbreCompbrbbles(Clbss<?> kc, Object k, Object x) {
        return (x == null || x.getClbss() != kc ? 0 :
                ((Compbrbble)k).compbreTo(x));
    }

    /**
     * Returns b power of two size for the given tbrget cbpbcity.
     */
    stbtic finbl int tbbleSizeFor(int cbp) {
        int n = cbp - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /* ---------------- Fields -------------- */

    /**
     * The tbble, initiblized on first use, bnd resized bs
     * necessbry. When bllocbted, length is blwbys b power of two.
     * (We blso tolerbte length zero in some operbtions to bllow
     * bootstrbpping mechbnics thbt bre currently not needed.)
     */
    trbnsient Node<K,V>[] tbble;

    /**
     * Holds cbched entrySet(). Note thbt AbstrbctMbp fields bre used
     * for keySet() bnd vblues().
     */
    trbnsient Set<Mbp.Entry<K,V>> entrySet;

    /**
     * The number of key-vblue mbppings contbined in this mbp.
     */
    trbnsient int size;

    /**
     * The number of times this HbshMbp hbs been structurblly modified
     * Structurbl modificbtions bre those thbt chbnge the number of mbppings in
     * the HbshMbp or otherwise modify its internbl structure (e.g.,
     * rehbsh).  This field is used to mbke iterbtors on Collection-views of
     * the HbshMbp fbil-fbst.  (See ConcurrentModificbtionException).
     */
    trbnsient int modCount;

    /**
     * The next size vblue bt which to resize (cbpbcity * lobd fbctor).
     *
     * @seribl
     */
    // (The jbvbdoc description is true upon seriblizbtion.
    // Additionblly, if the tbble brrby hbs not been bllocbted, this
    // field holds the initibl brrby cbpbcity, or zero signifying
    // DEFAULT_INITIAL_CAPACITY.)
    int threshold;

    /**
     * The lobd fbctor for the hbsh tbble.
     *
     * @seribl
     */
    finbl flobt lobdFbctor;

    /* ---------------- Public operbtions -------------- */

    /**
     * Constructs bn empty <tt>HbshMbp</tt> with the specified initibl
     * cbpbcity bnd lobd fbctor.
     *
     * @pbrbm  initiblCbpbcity the initibl cbpbcity
     * @pbrbm  lobdFbctor      the lobd fbctor
     * @throws IllegblArgumentException if the initibl cbpbcity is negbtive
     *         or the lobd fbctor is nonpositive
     */
    public HbshMbp(int initiblCbpbcity, flobt lobdFbctor) {
        if (initiblCbpbcity < 0)
            throw new IllegblArgumentException("Illegbl initibl cbpbcity: " +
                                               initiblCbpbcity);
        if (initiblCbpbcity > MAXIMUM_CAPACITY)
            initiblCbpbcity = MAXIMUM_CAPACITY;
        if (lobdFbctor <= 0 || Flobt.isNbN(lobdFbctor))
            throw new IllegblArgumentException("Illegbl lobd fbctor: " +
                                               lobdFbctor);
        this.lobdFbctor = lobdFbctor;
        this.threshold = tbbleSizeFor(initiblCbpbcity);
    }

    /**
     * Constructs bn empty <tt>HbshMbp</tt> with the specified initibl
     * cbpbcity bnd the defbult lobd fbctor (0.75).
     *
     * @pbrbm  initiblCbpbcity the initibl cbpbcity.
     * @throws IllegblArgumentException if the initibl cbpbcity is negbtive.
     */
    public HbshMbp(int initiblCbpbcity) {
        this(initiblCbpbcity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs bn empty <tt>HbshMbp</tt> with the defbult initibl cbpbcity
     * (16) bnd the defbult lobd fbctor (0.75).
     */
    public HbshMbp() {
        this.lobdFbctor = DEFAULT_LOAD_FACTOR; // bll other fields defbulted
    }

    /**
     * Constructs b new <tt>HbshMbp</tt> with the sbme mbppings bs the
     * specified <tt>Mbp</tt>.  The <tt>HbshMbp</tt> is crebted with
     * defbult lobd fbctor (0.75) bnd bn initibl cbpbcity sufficient to
     * hold the mbppings in the specified <tt>Mbp</tt>.
     *
     * @pbrbm   m the mbp whose mbppings bre to be plbced in this mbp
     * @throws  NullPointerException if the specified mbp is null
     */
    public HbshMbp(Mbp<? extends K, ? extends V> m) {
        this.lobdFbctor = DEFAULT_LOAD_FACTOR;
        putMbpEntries(m, fblse);
    }

    /**
     * Implements Mbp.putAll bnd Mbp constructor
     *
     * @pbrbm m the mbp
     * @pbrbm evict fblse when initiblly constructing this mbp, else
     * true (relbyed to method bfterNodeInsertion).
     */
    finbl void putMbpEntries(Mbp<? extends K, ? extends V> m, boolebn evict) {
        int s = m.size();
        if (s > 0) {
            if (tbble == null) { // pre-size
                flobt ft = ((flobt)s / lobdFbctor) + 1.0F;
                int t = ((ft < (flobt)MAXIMUM_CAPACITY) ?
                         (int)ft : MAXIMUM_CAPACITY);
                if (t > threshold)
                    threshold = tbbleSizeFor(t);
            }
            else if (s > threshold)
                resize();
            for (Mbp.Entry<? extends K, ? extends V> e : m.entrySet()) {
                K key = e.getKey();
                V vblue = e.getVblue();
                putVbl(hbsh(key), key, vblue, fblse, evict);
            }
        }
    }

    /**
     * Returns the number of key-vblue mbppings in this mbp.
     *
     * @return the number of key-vblue mbppings in this mbp
     */
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if this mbp contbins no key-vblue mbppings.
     *
     * @return <tt>true</tt> if this mbp contbins no key-vblue mbppings
     */
    public boolebn isEmpty() {
        return size == 0;
    }

    /**
     * Returns the vblue to which the specified key is mbpped,
     * or {@code null} if this mbp contbins no mbpping for the key.
     *
     * <p>More formblly, if this mbp contbins b mbpping from b key
     * {@code k} to b vblue {@code v} such thbt {@code (key==null ? k==null :
     * key.equbls(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There cbn be bt most one such mbpping.)
     *
     * <p>A return vblue of {@code null} does not <i>necessbrily</i>
     * indicbte thbt the mbp contbins no mbpping for the key; it's blso
     * possible thbt the mbp explicitly mbps the key to {@code null}.
     * The {@link #contbinsKey contbinsKey} operbtion mby be used to
     * distinguish these two cbses.
     *
     * @see #put(Object, Object)
     */
    public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hbsh(key), key)) == null ? null : e.vblue;
    }

    /**
     * Implements Mbp.get bnd relbted methods
     *
     * @pbrbm hbsh hbsh for key
     * @pbrbm key the key
     * @return the node, or null if none
     */
    finbl Node<K,V> getNode(int hbsh, Object key) {
        Node<K,V>[] tbb; Node<K,V> first, e; int n; K k;
        if ((tbb = tbble) != null && (n = tbb.length) > 0 &&
            (first = tbb[(n - 1) & hbsh]) != null) {
            if (first.hbsh == hbsh && // blwbys check first node
                ((k = first.key) == key || (key != null && key.equbls(k))))
                return first;
            if ((e = first.next) != null) {
                if (first instbnceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hbsh, key);
                do {
                    if (e.hbsh == hbsh &&
                        ((k = e.key) == key || (key != null && key.equbls(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    /**
     * Returns <tt>true</tt> if this mbp contbins b mbpping for the
     * specified key.
     *
     * @pbrbm   key   The key whose presence in this mbp is to be tested
     * @return <tt>true</tt> if this mbp contbins b mbpping for the specified
     * key.
     */
    public boolebn contbinsKey(Object key) {
        return getNode(hbsh(key), key) != null;
    }

    /**
     * Associbtes the specified vblue with the specified key in this mbp.
     * If the mbp previously contbined b mbpping for the key, the old
     * vblue is replbced.
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted
     * @pbrbm vblue vblue to be bssocibted with the specified key
     * @return the previous vblue bssocibted with <tt>key</tt>, or
     *         <tt>null</tt> if there wbs no mbpping for <tt>key</tt>.
     *         (A <tt>null</tt> return cbn blso indicbte thbt the mbp
     *         previously bssocibted <tt>null</tt> with <tt>key</tt>.)
     */
    public V put(K key, V vblue) {
        return putVbl(hbsh(key), key, vblue, fblse, true);
    }

    /**
     * Implements Mbp.put bnd relbted methods
     *
     * @pbrbm hbsh hbsh for key
     * @pbrbm key the key
     * @pbrbm vblue the vblue to put
     * @pbrbm onlyIfAbsent if true, don't chbnge existing vblue
     * @pbrbm evict if fblse, the tbble is in crebtion mode.
     * @return previous vblue, or null if none
     */
    finbl V putVbl(int hbsh, K key, V vblue, boolebn onlyIfAbsent,
                   boolebn evict) {
        Node<K,V>[] tbb; Node<K,V> p; int n, i;
        if ((tbb = tbble) == null || (n = tbb.length) == 0)
            n = (tbb = resize()).length;
        if ((p = tbb[i = (n - 1) & hbsh]) == null)
            tbb[i] = newNode(hbsh, key, vblue, null);
        else {
            Node<K,V> e; K k;
            if (p.hbsh == hbsh &&
                ((k = p.key) == key || (key != null && key.equbls(k))))
                e = p;
            else if (p instbnceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVbl(this, tbb, hbsh, key, vblue);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hbsh, key, vblue, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tbb, hbsh);
                        brebk;
                    }
                    if (e.hbsh == hbsh &&
                        ((k = e.key) == key || (key != null && key.equbls(k))))
                        brebk;
                    p = e;
                }
            }
            if (e != null) { // existing mbpping for key
                V oldVblue = e.vblue;
                if (!onlyIfAbsent || oldVblue == null)
                    e.vblue = vblue;
                bfterNodeAccess(e);
                return oldVblue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        bfterNodeInsertion(evict);
        return null;
    }

    /**
     * Initiblizes or doubles tbble size.  If null, bllocbtes in
     * bccord with initibl cbpbcity tbrget held in field threshold.
     * Otherwise, becbuse we bre using power-of-two expbnsion, the
     * elements from ebch bin must either stby bt sbme index, or move
     * with b power of two offset in the new tbble.
     *
     * @return the tbble
     */
    finbl Node<K,V>[] resize() {
        Node<K,V>[] oldTbb = tbble;
        int oldCbp = (oldTbb == null) ? 0 : oldTbb.length;
        int oldThr = threshold;
        int newCbp, newThr = 0;
        if (oldCbp > 0) {
            if (oldCbp >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTbb;
            }
            else if ((newCbp = oldCbp << 1) < MAXIMUM_CAPACITY &&
                     oldCbp >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        else if (oldThr > 0) // initibl cbpbcity wbs plbced in threshold
            newCbp = oldThr;
        else {               // zero initibl threshold signifies using defbults
            newCbp = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            flobt ft = (flobt)newCbp * lobdFbctor;
            newThr = (newCbp < MAXIMUM_CAPACITY && ft < (flobt)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWbrnings({"rbwtypes","unchecked"})
            Node<K,V>[] newTbb = (Node<K,V>[])new Node[newCbp];
        tbble = newTbb;
        if (oldTbb != null) {
            for (int j = 0; j < oldCbp; ++j) {
                Node<K,V> e;
                if ((e = oldTbb[j]) != null) {
                    oldTbb[j] = null;
                    if (e.next == null)
                        newTbb[e.hbsh & (newCbp - 1)] = e;
                    else if (e instbnceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTbb, j, oldCbp);
                    else { // preserve order
                        Node<K,V> loHebd = null, loTbil = null;
                        Node<K,V> hiHebd = null, hiTbil = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hbsh & oldCbp) == 0) {
                                if (loTbil == null)
                                    loHebd = e;
                                else
                                    loTbil.next = e;
                                loTbil = e;
                            }
                            else {
                                if (hiTbil == null)
                                    hiHebd = e;
                                else
                                    hiTbil.next = e;
                                hiTbil = e;
                            }
                        } while ((e = next) != null);
                        if (loTbil != null) {
                            loTbil.next = null;
                            newTbb[j] = loHebd;
                        }
                        if (hiTbil != null) {
                            hiTbil.next = null;
                            newTbb[j + oldCbp] = hiHebd;
                        }
                    }
                }
            }
        }
        return newTbb;
    }

    /**
     * Replbces bll linked nodes in bin bt index for given hbsh unless
     * tbble is too smbll, in which cbse resizes instebd.
     */
    finbl void treeifyBin(Node<K,V>[] tbb, int hbsh) {
        int n, index; Node<K,V> e;
        if (tbb == null || (n = tbb.length) < MIN_TREEIFY_CAPACITY)
            resize();
        else if ((e = tbb[index = (n - 1) & hbsh]) != null) {
            TreeNode<K,V> hd = null, tl = null;
            do {
                TreeNode<K,V> p = replbcementTreeNode(e, null);
                if (tl == null)
                    hd = p;
                else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            } while ((e = e.next) != null);
            if ((tbb[index] = hd) != null)
                hd.treeify(tbb);
        }
    }

    /**
     * Copies bll of the mbppings from the specified mbp to this mbp.
     * These mbppings will replbce bny mbppings thbt this mbp hbd for
     * bny of the keys currently in the specified mbp.
     *
     * @pbrbm m mbppings to be stored in this mbp
     * @throws NullPointerException if the specified mbp is null
     */
    public void putAll(Mbp<? extends K, ? extends V> m) {
        putMbpEntries(m, true);
    }

    /**
     * Removes the mbpping for the specified key from this mbp if present.
     *
     * @pbrbm  key key whose mbpping is to be removed from the mbp
     * @return the previous vblue bssocibted with <tt>key</tt>, or
     *         <tt>null</tt> if there wbs no mbpping for <tt>key</tt>.
     *         (A <tt>null</tt> return cbn blso indicbte thbt the mbp
     *         previously bssocibted <tt>null</tt> with <tt>key</tt>.)
     */
    public V remove(Object key) {
        Node<K,V> e;
        return (e = removeNode(hbsh(key), key, null, fblse, true)) == null ?
            null : e.vblue;
    }

    /**
     * Implements Mbp.remove bnd relbted methods
     *
     * @pbrbm hbsh hbsh for key
     * @pbrbm key the key
     * @pbrbm vblue the vblue to mbtch if mbtchVblue, else ignored
     * @pbrbm mbtchVblue if true only remove if vblue is equbl
     * @pbrbm movbble if fblse do not move other nodes while removing
     * @return the node, or null if none
     */
    finbl Node<K,V> removeNode(int hbsh, Object key, Object vblue,
                               boolebn mbtchVblue, boolebn movbble) {
        Node<K,V>[] tbb; Node<K,V> p; int n, index;
        if ((tbb = tbble) != null && (n = tbb.length) > 0 &&
            (p = tbb[index = (n - 1) & hbsh]) != null) {
            Node<K,V> node = null, e; K k; V v;
            if (p.hbsh == hbsh &&
                ((k = p.key) == key || (key != null && key.equbls(k))))
                node = p;
            else if ((e = p.next) != null) {
                if (p instbnceof TreeNode)
                    node = ((TreeNode<K,V>)p).getTreeNode(hbsh, key);
                else {
                    do {
                        if (e.hbsh == hbsh &&
                            ((k = e.key) == key ||
                             (key != null && key.equbls(k)))) {
                            node = e;
                            brebk;
                        }
                        p = e;
                    } while ((e = e.next) != null);
                }
            }
            if (node != null && (!mbtchVblue || (v = node.vblue) == vblue ||
                                 (vblue != null && vblue.equbls(v)))) {
                if (node instbnceof TreeNode)
                    ((TreeNode<K,V>)node).removeTreeNode(this, tbb, movbble);
                else if (node == p)
                    tbb[index] = node.next;
                else
                    p.next = node.next;
                ++modCount;
                --size;
                bfterNodeRemovbl(node);
                return node;
            }
        }
        return null;
    }

    /**
     * Removes bll of the mbppings from this mbp.
     * The mbp will be empty bfter this cbll returns.
     */
    public void clebr() {
        Node<K,V>[] tbb;
        modCount++;
        if ((tbb = tbble) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tbb.length; ++i)
                tbb[i] = null;
        }
    }

    /**
     * Returns <tt>true</tt> if this mbp mbps one or more keys to the
     * specified vblue.
     *
     * @pbrbm vblue vblue whose presence in this mbp is to be tested
     * @return <tt>true</tt> if this mbp mbps one or more keys to the
     *         specified vblue
     */
    public boolebn contbinsVblue(Object vblue) {
        Node<K,V>[] tbb; V v;
        if ((tbb = tbble) != null && size > 0) {
            for (Node<K, V> e : tbb) {
                for (; e != null; e = e.next) {
                    if ((v = e.vblue) == vblue ||
                        (vblue != null && vblue.equbls(v)))
                        return true;
                }
            }
        }
        return fblse;
    }

    /**
     * Returns b {@link Set} view of the keys contbined in this mbp.
     * The set is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the set, bnd vice-versb.  If the mbp is modified
     * while bn iterbtion over the set is in progress (except through
     * the iterbtor's own <tt>remove</tt> operbtion), the results of
     * the iterbtion bre undefined.  The set supports element removbl,
     * which removes the corresponding mbpping from the mbp, vib the
     * <tt>Iterbtor.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retbinAll</tt>, bnd <tt>clebr</tt>
     * operbtions.  It does not support the <tt>bdd</tt> or <tt>bddAll</tt>
     * operbtions.
     *
     * @return b set view of the keys contbined in this mbp
     */
    public Set<K> keySet() {
        Set<K> ks;
        return (ks = keySet) == null ? (keySet = new KeySet()) : ks;
    }

    finbl clbss KeySet extends AbstrbctSet<K> {
        public finbl int size()                 { return size; }
        public finbl void clebr()               { HbshMbp.this.clebr(); }
        public finbl Iterbtor<K> iterbtor()     { return new KeyIterbtor(); }
        public finbl boolebn contbins(Object o) { return contbinsKey(o); }
        public finbl boolebn remove(Object key) {
            return removeNode(hbsh(key), key, null, fblse, true) != null;
        }
        public finbl Spliterbtor<K> spliterbtor() {
            return new KeySpliterbtor<>(HbshMbp.this, 0, -1, 0, 0);
        }
        public finbl void forEbch(Consumer<? super K> bction) {
            Node<K,V>[] tbb;
            if (bction == null)
                throw new NullPointerException();
            if (size > 0 && (tbb = tbble) != null) {
                int mc = modCount;
                for (Node<K, V> e : tbb) {
                    for (; e != null; e = e.next)
                        bction.bccept(e.key);
                }
                if (modCount != mc)
                    throw new ConcurrentModificbtionException();
            }
        }
    }

    /**
     * Returns b {@link Collection} view of the vblues contbined in this mbp.
     * The collection is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the collection, bnd vice-versb.  If the mbp is
     * modified while bn iterbtion over the collection is in progress
     * (except through the iterbtor's own <tt>remove</tt> operbtion),
     * the results of the iterbtion bre undefined.  The collection
     * supports element removbl, which removes the corresponding
     * mbpping from the mbp, vib the <tt>Iterbtor.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retbinAll</tt> bnd <tt>clebr</tt> operbtions.  It does not
     * support the <tt>bdd</tt> or <tt>bddAll</tt> operbtions.
     *
     * @return b view of the vblues contbined in this mbp
     */
    public Collection<V> vblues() {
        Collection<V> vs;
        return (vs = vblues) == null ? (vblues = new Vblues()) : vs;
    }

    finbl clbss Vblues extends AbstrbctCollection<V> {
        public finbl int size()                 { return size; }
        public finbl void clebr()               { HbshMbp.this.clebr(); }
        public finbl Iterbtor<V> iterbtor()     { return new VblueIterbtor(); }
        public finbl boolebn contbins(Object o) { return contbinsVblue(o); }
        public finbl Spliterbtor<V> spliterbtor() {
            return new VblueSpliterbtor<>(HbshMbp.this, 0, -1, 0, 0);
        }
        public finbl void forEbch(Consumer<? super V> bction) {
            Node<K,V>[] tbb;
            if (bction == null)
                throw new NullPointerException();
            if (size > 0 && (tbb = tbble) != null) {
                int mc = modCount;
                for (Node<K, V> e : tbb) {
                    for (; e != null; e = e.next)
                        bction.bccept(e.vblue);
                }
                if (modCount != mc)
                    throw new ConcurrentModificbtionException();
            }
        }
    }

    /**
     * Returns b {@link Set} view of the mbppings contbined in this mbp.
     * The set is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the set, bnd vice-versb.  If the mbp is modified
     * while bn iterbtion over the set is in progress (except through
     * the iterbtor's own <tt>remove</tt> operbtion, or through the
     * <tt>setVblue</tt> operbtion on b mbp entry returned by the
     * iterbtor) the results of the iterbtion bre undefined.  The set
     * supports element removbl, which removes the corresponding
     * mbpping from the mbp, vib the <tt>Iterbtor.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retbinAll</tt> bnd
     * <tt>clebr</tt> operbtions.  It does not support the
     * <tt>bdd</tt> or <tt>bddAll</tt> operbtions.
     *
     * @return b set view of the mbppings contbined in this mbp
     */
    public Set<Mbp.Entry<K,V>> entrySet() {
        Set<Mbp.Entry<K,V>> es;
        return (es = entrySet) == null ? (entrySet = new EntrySet()) : es;
    }

    finbl clbss EntrySet extends AbstrbctSet<Mbp.Entry<K,V>> {
        public finbl int size()                 { return size; }
        public finbl void clebr()               { HbshMbp.this.clebr(); }
        public finbl Iterbtor<Mbp.Entry<K,V>> iterbtor() {
            return new EntryIterbtor();
        }
        public finbl boolebn contbins(Object o) {
            if (!(o instbnceof Mbp.Entry))
                return fblse;
            Mbp.Entry<?,?> e = (Mbp.Entry<?,?>) o;
            Object key = e.getKey();
            Node<K,V> cbndidbte = getNode(hbsh(key), key);
            return cbndidbte != null && cbndidbte.equbls(e);
        }
        public finbl boolebn remove(Object o) {
            if (o instbnceof Mbp.Entry) {
                Mbp.Entry<?,?> e = (Mbp.Entry<?,?>) o;
                Object key = e.getKey();
                Object vblue = e.getVblue();
                return removeNode(hbsh(key), key, vblue, true, true) != null;
            }
            return fblse;
        }
        public finbl Spliterbtor<Mbp.Entry<K,V>> spliterbtor() {
            return new EntrySpliterbtor<>(HbshMbp.this, 0, -1, 0, 0);
        }
        public finbl void forEbch(Consumer<? super Mbp.Entry<K,V>> bction) {
            Node<K,V>[] tbb;
            if (bction == null)
                throw new NullPointerException();
            if (size > 0 && (tbb = tbble) != null) {
                int mc = modCount;
                for (Node<K, V> e : tbb) {
                    for (; e != null; e = e.next)
                        bction.bccept(e);
                }
                if (modCount != mc)
                    throw new ConcurrentModificbtionException();
            }
        }
    }

    // Overrides of JDK8 Mbp extension methods

    @Override
    public V getOrDefbult(Object key, V defbultVblue) {
        Node<K,V> e;
        return (e = getNode(hbsh(key), key)) == null ? defbultVblue : e.vblue;
    }

    @Override
    public V putIfAbsent(K key, V vblue) {
        return putVbl(hbsh(key), key, vblue, true, true);
    }

    @Override
    public boolebn remove(Object key, Object vblue) {
        return removeNode(hbsh(key), key, vblue, true, true) != null;
    }

    @Override
    public boolebn replbce(K key, V oldVblue, V newVblue) {
        Node<K,V> e; V v;
        if ((e = getNode(hbsh(key), key)) != null &&
            ((v = e.vblue) == oldVblue || (v != null && v.equbls(oldVblue)))) {
            e.vblue = newVblue;
            bfterNodeAccess(e);
            return true;
        }
        return fblse;
    }

    @Override
    public V replbce(K key, V vblue) {
        Node<K,V> e;
        if ((e = getNode(hbsh(key), key)) != null) {
            V oldVblue = e.vblue;
            e.vblue = vblue;
            bfterNodeAccess(e);
            return oldVblue;
        }
        return null;
    }

    @Override
    public V computeIfAbsent(K key,
                             Function<? super K, ? extends V> mbppingFunction) {
        if (mbppingFunction == null)
            throw new NullPointerException();
        int hbsh = hbsh(key);
        Node<K,V>[] tbb; Node<K,V> first; int n, i;
        int binCount = 0;
        TreeNode<K,V> t = null;
        Node<K,V> old = null;
        if (size > threshold || (tbb = tbble) == null ||
            (n = tbb.length) == 0)
            n = (tbb = resize()).length;
        if ((first = tbb[i = (n - 1) & hbsh]) != null) {
            if (first instbnceof TreeNode)
                old = (t = (TreeNode<K,V>)first).getTreeNode(hbsh, key);
            else {
                Node<K,V> e = first; K k;
                do {
                    if (e.hbsh == hbsh &&
                        ((k = e.key) == key || (key != null && key.equbls(k)))) {
                        old = e;
                        brebk;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
            V oldVblue;
            if (old != null && (oldVblue = old.vblue) != null) {
                bfterNodeAccess(old);
                return oldVblue;
            }
        }
        V v = mbppingFunction.bpply(key);
        if (v == null) {
            return null;
        } else if (old != null) {
            old.vblue = v;
            bfterNodeAccess(old);
            return v;
        }
        else if (t != null)
            t.putTreeVbl(this, tbb, hbsh, key, v);
        else {
            tbb[i] = newNode(hbsh, key, v, first);
            if (binCount >= TREEIFY_THRESHOLD - 1)
                treeifyBin(tbb, hbsh);
        }
        ++modCount;
        ++size;
        bfterNodeInsertion(true);
        return v;
    }

    public V computeIfPresent(K key,
                              BiFunction<? super K, ? super V, ? extends V> rembppingFunction) {
        if (rembppingFunction == null)
            throw new NullPointerException();
        Node<K,V> e; V oldVblue;
        int hbsh = hbsh(key);
        if ((e = getNode(hbsh, key)) != null &&
            (oldVblue = e.vblue) != null) {
            V v = rembppingFunction.bpply(key, oldVblue);
            if (v != null) {
                e.vblue = v;
                bfterNodeAccess(e);
                return v;
            }
            else
                removeNode(hbsh, key, null, fblse, true);
        }
        return null;
    }

    @Override
    public V compute(K key,
                     BiFunction<? super K, ? super V, ? extends V> rembppingFunction) {
        if (rembppingFunction == null)
            throw new NullPointerException();
        int hbsh = hbsh(key);
        Node<K,V>[] tbb; Node<K,V> first; int n, i;
        int binCount = 0;
        TreeNode<K,V> t = null;
        Node<K,V> old = null;
        if (size > threshold || (tbb = tbble) == null ||
            (n = tbb.length) == 0)
            n = (tbb = resize()).length;
        if ((first = tbb[i = (n - 1) & hbsh]) != null) {
            if (first instbnceof TreeNode)
                old = (t = (TreeNode<K,V>)first).getTreeNode(hbsh, key);
            else {
                Node<K,V> e = first; K k;
                do {
                    if (e.hbsh == hbsh &&
                        ((k = e.key) == key || (key != null && key.equbls(k)))) {
                        old = e;
                        brebk;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
        }
        V oldVblue = (old == null) ? null : old.vblue;
        V v = rembppingFunction.bpply(key, oldVblue);
        if (old != null) {
            if (v != null) {
                old.vblue = v;
                bfterNodeAccess(old);
            }
            else
                removeNode(hbsh, key, null, fblse, true);
        }
        else if (v != null) {
            if (t != null)
                t.putTreeVbl(this, tbb, hbsh, key, v);
            else {
                tbb[i] = newNode(hbsh, key, v, first);
                if (binCount >= TREEIFY_THRESHOLD - 1)
                    treeifyBin(tbb, hbsh);
            }
            ++modCount;
            ++size;
            bfterNodeInsertion(true);
        }
        return v;
    }

    @Override
    public V merge(K key, V vblue,
                   BiFunction<? super V, ? super V, ? extends V> rembppingFunction) {
        if (vblue == null)
            throw new NullPointerException();
        if (rembppingFunction == null)
            throw new NullPointerException();
        int hbsh = hbsh(key);
        Node<K,V>[] tbb; Node<K,V> first; int n, i;
        int binCount = 0;
        TreeNode<K,V> t = null;
        Node<K,V> old = null;
        if (size > threshold || (tbb = tbble) == null ||
            (n = tbb.length) == 0)
            n = (tbb = resize()).length;
        if ((first = tbb[i = (n - 1) & hbsh]) != null) {
            if (first instbnceof TreeNode)
                old = (t = (TreeNode<K,V>)first).getTreeNode(hbsh, key);
            else {
                Node<K,V> e = first; K k;
                do {
                    if (e.hbsh == hbsh &&
                        ((k = e.key) == key || (key != null && key.equbls(k)))) {
                        old = e;
                        brebk;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
        }
        if (old != null) {
            V v;
            if (old.vblue != null)
                v = rembppingFunction.bpply(old.vblue, vblue);
            else
                v = vblue;
            if (v != null) {
                old.vblue = v;
                bfterNodeAccess(old);
            }
            else
                removeNode(hbsh, key, null, fblse, true);
            return v;
        }
        if (vblue != null) {
            if (t != null)
                t.putTreeVbl(this, tbb, hbsh, key, vblue);
            else {
                tbb[i] = newNode(hbsh, key, vblue, first);
                if (binCount >= TREEIFY_THRESHOLD - 1)
                    treeifyBin(tbb, hbsh);
            }
            ++modCount;
            ++size;
            bfterNodeInsertion(true);
        }
        return vblue;
    }

    @Override
    public void forEbch(BiConsumer<? super K, ? super V> bction) {
        Node<K,V>[] tbb;
        if (bction == null)
            throw new NullPointerException();
        if (size > 0 && (tbb = tbble) != null) {
            int mc = modCount;
            for (Node<K, V> e : tbb) {
                for (; e != null; e = e.next)
                    bction.bccept(e.key, e.vblue);
            }
            if (modCount != mc)
                throw new ConcurrentModificbtionException();
        }
    }

    @Override
    public void replbceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Node<K,V>[] tbb;
        if (function == null)
            throw new NullPointerException();
        if (size > 0 && (tbb = tbble) != null) {
            int mc = modCount;
            for (Node<K, V> e : tbb) {
                for (; e != null; e = e.next) {
                    e.vblue = function.bpply(e.key, e.vblue);
                }
            }
            if (modCount != mc)
                throw new ConcurrentModificbtionException();
        }
    }

    /* ------------------------------------------------------------ */
    // Cloning bnd seriblizbtion

    /**
     * Returns b shbllow copy of this <tt>HbshMbp</tt> instbnce: the keys bnd
     * vblues themselves bre not cloned.
     *
     * @return b shbllow copy of this mbp
     */
    @SuppressWbrnings("unchecked")
    @Override
    public Object clone() {
        HbshMbp<K,V> result;
        try {
            result = (HbshMbp<K,V>)super.clone();
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError(e);
        }
        result.reinitiblize();
        result.putMbpEntries(this, fblse);
        return result;
    }

    // These methods bre blso used when seriblizing HbshSets
    finbl flobt lobdFbctor() { return lobdFbctor; }
    finbl int cbpbcity() {
        return (tbble != null) ? tbble.length :
            (threshold > 0) ? threshold :
            DEFAULT_INITIAL_CAPACITY;
    }

    /**
     * Sbve the stbte of the <tt>HbshMbp</tt> instbnce to b strebm (i.e.,
     * seriblize it).
     *
     * @seriblDbtb The <i>cbpbcity</i> of the HbshMbp (the length of the
     *             bucket brrby) is emitted (int), followed by the
     *             <i>size</i> (bn int, the number of key-vblue
     *             mbppings), followed by the key (Object) bnd vblue (Object)
     *             for ebch key-vblue mbpping.  The key-vblue mbppings bre
     *             emitted in no pbrticulbr order.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws IOException {
        int buckets = cbpbcity();
        // Write out the threshold, lobdfbctor, bnd bny hidden stuff
        s.defbultWriteObject();
        s.writeInt(buckets);
        s.writeInt(size);
        internblWriteEntries(s);
    }

    /**
     * Reconstitute the {@code HbshMbp} instbnce from b strebm (i.e.,
     * deseriblize it).
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException {
        // Rebd in the threshold (ignored), lobdfbctor, bnd bny hidden stuff
        s.defbultRebdObject();
        reinitiblize();
        if (lobdFbctor <= 0 || Flobt.isNbN(lobdFbctor))
            throw new InvblidObjectException("Illegbl lobd fbctor: " +
                                             lobdFbctor);
        s.rebdInt();                // Rebd bnd ignore number of buckets
        int mbppings = s.rebdInt(); // Rebd number of mbppings (size)
        if (mbppings < 0)
            throw new InvblidObjectException("Illegbl mbppings count: " +
                                             mbppings);
        else if (mbppings > 0) { // (if zero, use defbults)
            // Size the tbble using given lobd fbctor only if within
            // rbnge of 0.25...4.0
            flobt lf = Mbth.min(Mbth.mbx(0.25f, lobdFbctor), 4.0f);
            flobt fc = (flobt)mbppings / lf + 1.0f;
            int cbp = ((fc < DEFAULT_INITIAL_CAPACITY) ?
                       DEFAULT_INITIAL_CAPACITY :
                       (fc >= MAXIMUM_CAPACITY) ?
                       MAXIMUM_CAPACITY :
                       tbbleSizeFor((int)fc));
            flobt ft = (flobt)cbp * lf;
            threshold = ((cbp < MAXIMUM_CAPACITY && ft < MAXIMUM_CAPACITY) ?
                         (int)ft : Integer.MAX_VALUE);
            @SuppressWbrnings({"rbwtypes","unchecked"})
                Node<K,V>[] tbb = (Node<K,V>[])new Node[cbp];
            tbble = tbb;

            // Rebd the keys bnd vblues, bnd put the mbppings in the HbshMbp
            for (int i = 0; i < mbppings; i++) {
                @SuppressWbrnings("unchecked")
                    K key = (K) s.rebdObject();
                @SuppressWbrnings("unchecked")
                    V vblue = (V) s.rebdObject();
                putVbl(hbsh(key), key, vblue, fblse, fblse);
            }
        }
    }

    /* ------------------------------------------------------------ */
    // iterbtors

    bbstrbct clbss HbshIterbtor {
        Node<K,V> next;        // next entry to return
        Node<K,V> current;     // current entry
        int expectedModCount;  // for fbst-fbil
        int index;             // current slot

        HbshIterbtor() {
            expectedModCount = modCount;
            Node<K,V>[] t = tbble;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { // bdvbnce to first entry
                do {} while (index < t.length && (next = t[index++]) == null);
            }
        }

        public finbl boolebn hbsNext() {
            return next != null;
        }

        finbl Node<K,V> nextNode() {
            Node<K,V>[] t;
            Node<K,V> e = next;
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();
            if (e == null)
                throw new NoSuchElementException();
            if ((next = (current = e).next) == null && (t = tbble) != null) {
                do {} while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }

        public finbl void remove() {
            Node<K,V> p = current;
            if (p == null)
                throw new IllegblStbteException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();
            current = null;
            K key = p.key;
            removeNode(hbsh(key), key, null, fblse, fblse);
            expectedModCount = modCount;
        }
    }

    finbl clbss KeyIterbtor extends HbshIterbtor
        implements Iterbtor<K> {
        public finbl K next() { return nextNode().key; }
    }

    finbl clbss VblueIterbtor extends HbshIterbtor
        implements Iterbtor<V> {
        public finbl V next() { return nextNode().vblue; }
    }

    finbl clbss EntryIterbtor extends HbshIterbtor
        implements Iterbtor<Mbp.Entry<K,V>> {
        public finbl Mbp.Entry<K,V> next() { return nextNode(); }
    }

    /* ------------------------------------------------------------ */
    // spliterbtors

    stbtic clbss HbshMbpSpliterbtor<K,V> {
        finbl HbshMbp<K,V> mbp;
        Node<K,V> current;          // current node
        int index;                  // current index, modified on bdvbnce/split
        int fence;                  // one pbst lbst index
        int est;                    // size estimbte
        int expectedModCount;       // for comodificbtion checks

        HbshMbpSpliterbtor(HbshMbp<K,V> m, int origin,
                           int fence, int est,
                           int expectedModCount) {
            this.mbp = m;
            this.index = origin;
            this.fence = fence;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        finbl int getFence() { // initiblize fence bnd size on first use
            int hi;
            if ((hi = fence) < 0) {
                HbshMbp<K,V> m = mbp;
                est = m.size;
                expectedModCount = m.modCount;
                Node<K,V>[] tbb = m.tbble;
                hi = fence = (tbb == null) ? 0 : tbb.length;
            }
            return hi;
        }

        public finbl long estimbteSize() {
            getFence(); // force init
            return (long) est;
        }
    }

    stbtic finbl clbss KeySpliterbtor<K,V>
        extends HbshMbpSpliterbtor<K,V>
        implements Spliterbtor<K> {
        KeySpliterbtor(HbshMbp<K,V> m, int origin, int fence, int est,
                       int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public KeySpliterbtor<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                new KeySpliterbtor<>(mbp, lo, index = mid, est >>>= 1,
                                        expectedModCount);
        }

        public void forEbchRembining(Consumer<? super K> bction) {
            int i, hi, mc;
            if (bction == null)
                throw new NullPointerException();
            HbshMbp<K,V> m = mbp;
            Node<K,V>[] tbb = m.tbble;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tbb == null) ? 0 : tbb.length;
            }
            else
                mc = expectedModCount;
            if (tbb != null && tbb.length >= hi &&
                (i = index) >= 0 && (i < (index = hi) || current != null)) {
                Node<K,V> p = current;
                current = null;
                do {
                    if (p == null)
                        p = tbb[i++];
                    else {
                        bction.bccept(p.key);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc)
                    throw new ConcurrentModificbtionException();
            }
        }

        public boolebn tryAdvbnce(Consumer<? super K> bction) {
            int hi;
            if (bction == null)
                throw new NullPointerException();
            Node<K,V>[] tbb = mbp.tbble;
            if (tbb != null && tbb.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tbb[index++];
                    else {
                        K k = current.key;
                        current = current.next;
                        bction.bccept(k);
                        if (mbp.modCount != expectedModCount)
                            throw new ConcurrentModificbtionException();
                        return true;
                    }
                }
            }
            return fblse;
        }

        public int chbrbcteristics() {
            return (fence < 0 || est == mbp.size ? Spliterbtor.SIZED : 0) |
                Spliterbtor.DISTINCT;
        }
    }

    stbtic finbl clbss VblueSpliterbtor<K,V>
        extends HbshMbpSpliterbtor<K,V>
        implements Spliterbtor<V> {
        VblueSpliterbtor(HbshMbp<K,V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public VblueSpliterbtor<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                new VblueSpliterbtor<>(mbp, lo, index = mid, est >>>= 1,
                                          expectedModCount);
        }

        public void forEbchRembining(Consumer<? super V> bction) {
            int i, hi, mc;
            if (bction == null)
                throw new NullPointerException();
            HbshMbp<K,V> m = mbp;
            Node<K,V>[] tbb = m.tbble;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tbb == null) ? 0 : tbb.length;
            }
            else
                mc = expectedModCount;
            if (tbb != null && tbb.length >= hi &&
                (i = index) >= 0 && (i < (index = hi) || current != null)) {
                Node<K,V> p = current;
                current = null;
                do {
                    if (p == null)
                        p = tbb[i++];
                    else {
                        bction.bccept(p.vblue);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc)
                    throw new ConcurrentModificbtionException();
            }
        }

        public boolebn tryAdvbnce(Consumer<? super V> bction) {
            int hi;
            if (bction == null)
                throw new NullPointerException();
            Node<K,V>[] tbb = mbp.tbble;
            if (tbb != null && tbb.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tbb[index++];
                    else {
                        V v = current.vblue;
                        current = current.next;
                        bction.bccept(v);
                        if (mbp.modCount != expectedModCount)
                            throw new ConcurrentModificbtionException();
                        return true;
                    }
                }
            }
            return fblse;
        }

        public int chbrbcteristics() {
            return (fence < 0 || est == mbp.size ? Spliterbtor.SIZED : 0);
        }
    }

    stbtic finbl clbss EntrySpliterbtor<K,V>
        extends HbshMbpSpliterbtor<K,V>
        implements Spliterbtor<Mbp.Entry<K,V>> {
        EntrySpliterbtor(HbshMbp<K,V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public EntrySpliterbtor<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                new EntrySpliterbtor<>(mbp, lo, index = mid, est >>>= 1,
                                          expectedModCount);
        }

        public void forEbchRembining(Consumer<? super Mbp.Entry<K,V>> bction) {
            int i, hi, mc;
            if (bction == null)
                throw new NullPointerException();
            HbshMbp<K,V> m = mbp;
            Node<K,V>[] tbb = m.tbble;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tbb == null) ? 0 : tbb.length;
            }
            else
                mc = expectedModCount;
            if (tbb != null && tbb.length >= hi &&
                (i = index) >= 0 && (i < (index = hi) || current != null)) {
                Node<K,V> p = current;
                current = null;
                do {
                    if (p == null)
                        p = tbb[i++];
                    else {
                        bction.bccept(p);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc)
                    throw new ConcurrentModificbtionException();
            }
        }

        public boolebn tryAdvbnce(Consumer<? super Mbp.Entry<K,V>> bction) {
            int hi;
            if (bction == null)
                throw new NullPointerException();
            Node<K,V>[] tbb = mbp.tbble;
            if (tbb != null && tbb.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tbb[index++];
                    else {
                        Node<K,V> e = current;
                        current = current.next;
                        bction.bccept(e);
                        if (mbp.modCount != expectedModCount)
                            throw new ConcurrentModificbtionException();
                        return true;
                    }
                }
            }
            return fblse;
        }

        public int chbrbcteristics() {
            return (fence < 0 || est == mbp.size ? Spliterbtor.SIZED : 0) |
                Spliterbtor.DISTINCT;
        }
    }

    /* ------------------------------------------------------------ */
    // LinkedHbshMbp support


    /*
     * The following pbckbge-protected methods bre designed to be
     * overridden by LinkedHbshMbp, but not by bny other subclbss.
     * Nebrly bll other internbl methods bre blso pbckbge-protected
     * but bre declbred finbl, so cbn be used by LinkedHbshMbp, view
     * clbsses, bnd HbshSet.
     */

    // Crebte b regulbr (non-tree) node
    Node<K,V> newNode(int hbsh, K key, V vblue, Node<K,V> next) {
        return new Node<>(hbsh, key, vblue, next);
    }

    // For conversion from TreeNodes to plbin nodes
    Node<K,V> replbcementNode(Node<K,V> p, Node<K,V> next) {
        return new Node<>(p.hbsh, p.key, p.vblue, next);
    }

    // Crebte b tree bin node
    TreeNode<K,V> newTreeNode(int hbsh, K key, V vblue, Node<K,V> next) {
        return new TreeNode<>(hbsh, key, vblue, next);
    }

    // For treeifyBin
    TreeNode<K,V> replbcementTreeNode(Node<K,V> p, Node<K,V> next) {
        return new TreeNode<>(p.hbsh, p.key, p.vblue, next);
    }

    /**
     * Reset to initibl defbult stbte.  Cblled by clone bnd rebdObject.
     */
    void reinitiblize() {
        tbble = null;
        entrySet = null;
        keySet = null;
        vblues = null;
        modCount = 0;
        threshold = 0;
        size = 0;
    }

    // Cbllbbcks to bllow LinkedHbshMbp post-bctions
    void bfterNodeAccess(Node<K,V> p) { }
    void bfterNodeInsertion(boolebn evict) { }
    void bfterNodeRemovbl(Node<K,V> p) { }

    // Cblled only from writeObject, to ensure compbtible ordering.
    void internblWriteEntries(jbvb.io.ObjectOutputStrebm s) throws IOException {
        Node<K,V>[] tbb;
        if (size > 0 && (tbb = tbble) != null) {
            for (Node<K, V> e : tbb) {
                for (; e != null; e = e.next) {
                    s.writeObject(e.key);
                    s.writeObject(e.vblue);
                }
            }
        }
    }

    /* ------------------------------------------------------------ */
    // Tree bins

    /**
     * Entry for Tree bins. Extends LinkedHbshMbp.Entry (which in turn
     * extends Node) so cbn be used bs extension of either regulbr or
     * linked node.
     */
    stbtic finbl clbss TreeNode<K,V> extends LinkedHbshMbp.Entry<K,V> {
        TreeNode<K,V> pbrent;  // red-blbck tree links
        TreeNode<K,V> left;
        TreeNode<K,V> right;
        TreeNode<K,V> prev;    // needed to unlink next upon deletion
        boolebn red;
        TreeNode(int hbsh, K key, V vbl, Node<K,V> next) {
            super(hbsh, key, vbl, next);
        }

        /**
         * Returns root of tree contbining this node.
         */
        finbl TreeNode<K,V> root() {
            for (TreeNode<K,V> r = this, p;;) {
                if ((p = r.pbrent) == null)
                    return r;
                r = p;
            }
        }

        /**
         * Ensures thbt the given root is the first node of its bin.
         */
        stbtic <K,V> void moveRootToFront(Node<K,V>[] tbb, TreeNode<K,V> root) {
            int n;
            if (root != null && tbb != null && (n = tbb.length) > 0) {
                int index = (n - 1) & root.hbsh;
                TreeNode<K,V> first = (TreeNode<K,V>)tbb[index];
                if (root != first) {
                    Node<K,V> rn;
                    tbb[index] = root;
                    TreeNode<K,V> rp = root.prev;
                    if ((rn = root.next) != null)
                        ((TreeNode<K,V>)rn).prev = rp;
                    if (rp != null)
                        rp.next = rn;
                    if (first != null)
                        first.prev = root;
                    root.next = first;
                    root.prev = null;
                }
                bssert checkInvbribnts(root);
            }
        }

        /**
         * Finds the node stbrting bt root p with the given hbsh bnd key.
         * The kc brgument cbches compbrbbleClbssFor(key) upon first use
         * compbring keys.
         */
        finbl TreeNode<K,V> find(int h, Object k, Clbss<?> kc) {
            TreeNode<K,V> p = this;
            do {
                int ph, dir; K pk;
                TreeNode<K,V> pl = p.left, pr = p.right, q;
                if ((ph = p.hbsh) > h)
                    p = pl;
                else if (ph < h)
                    p = pr;
                else if ((pk = p.key) == k || (k != null && k.equbls(pk)))
                    return p;
                else if (pl == null)
                    p = pr;
                else if (pr == null)
                    p = pl;
                else if ((kc != null ||
                          (kc = compbrbbleClbssFor(k)) != null) &&
                         (dir = compbreCompbrbbles(kc, k, pk)) != 0)
                    p = (dir < 0) ? pl : pr;
                else if ((q = pr.find(h, k, kc)) != null)
                    return q;
                else
                    p = pl;
            } while (p != null);
            return null;
        }

        /**
         * Cblls find for root node.
         */
        finbl TreeNode<K,V> getTreeNode(int h, Object k) {
            return ((pbrent != null) ? root() : this).find(h, k, null);
        }

        /**
         * Tie-brebking utility for ordering insertions when equbl
         * hbshCodes bnd non-compbrbble. We don't require b totbl
         * order, just b consistent insertion rule to mbintbin
         * equivblence bcross rebblbncings. Tie-brebking further thbn
         * necessbry simplifies testing b bit.
         */
        stbtic int tieBrebkOrder(Object b, Object b) {
            int d;
            if (b == null || b == null ||
                (d = b.getClbss().getNbme().
                 compbreTo(b.getClbss().getNbme())) == 0)
                d = (System.identityHbshCode(b) <= System.identityHbshCode(b) ?
                     -1 : 1);
            return d;
        }

        /**
         * Forms tree of the nodes linked from this node.
         * @return root of tree
         */
        finbl void treeify(Node<K,V>[] tbb) {
            TreeNode<K,V> root = null;
            for (TreeNode<K,V> x = this, next; x != null; x = next) {
                next = (TreeNode<K,V>)x.next;
                x.left = x.right = null;
                if (root == null) {
                    x.pbrent = null;
                    x.red = fblse;
                    root = x;
                }
                else {
                    K k = x.key;
                    int h = x.hbsh;
                    Clbss<?> kc = null;
                    for (TreeNode<K,V> p = root;;) {
                        int dir, ph;
                        K pk = p.key;
                        if ((ph = p.hbsh) > h)
                            dir = -1;
                        else if (ph < h)
                            dir = 1;
                        else if ((kc == null &&
                                  (kc = compbrbbleClbssFor(k)) == null) ||
                                 (dir = compbreCompbrbbles(kc, k, pk)) == 0)
                            dir = tieBrebkOrder(k, pk);

                        TreeNode<K,V> xp = p;
                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
                            x.pbrent = xp;
                            if (dir <= 0)
                                xp.left = x;
                            else
                                xp.right = x;
                            root = bblbnceInsertion(root, x);
                            brebk;
                        }
                    }
                }
            }
            moveRootToFront(tbb, root);
        }

        /**
         * Returns b list of non-TreeNodes replbcing those linked from
         * this node.
         */
        finbl Node<K,V> untreeify(HbshMbp<K,V> mbp) {
            Node<K,V> hd = null, tl = null;
            for (Node<K,V> q = this; q != null; q = q.next) {
                Node<K,V> p = mbp.replbcementNode(q, null);
                if (tl == null)
                    hd = p;
                else
                    tl.next = p;
                tl = p;
            }
            return hd;
        }

        /**
         * Tree version of putVbl.
         */
        finbl TreeNode<K,V> putTreeVbl(HbshMbp<K,V> mbp, Node<K,V>[] tbb,
                                       int h, K k, V v) {
            Clbss<?> kc = null;
            boolebn sebrched = fblse;
            TreeNode<K,V> root = (pbrent != null) ? root() : this;
            for (TreeNode<K,V> p = root;;) {
                int dir, ph; K pk;
                if ((ph = p.hbsh) > h)
                    dir = -1;
                else if (ph < h)
                    dir = 1;
                else if ((pk = p.key) == k || (k != null && k.equbls(pk)))
                    return p;
                else if ((kc == null &&
                          (kc = compbrbbleClbssFor(k)) == null) ||
                         (dir = compbreCompbrbbles(kc, k, pk)) == 0) {
                    if (!sebrched) {
                        TreeNode<K,V> q, ch;
                        sebrched = true;
                        if (((ch = p.left) != null &&
                             (q = ch.find(h, k, kc)) != null) ||
                            ((ch = p.right) != null &&
                             (q = ch.find(h, k, kc)) != null))
                            return q;
                    }
                    dir = tieBrebkOrder(k, pk);
                }

                TreeNode<K,V> xp = p;
                if ((p = (dir <= 0) ? p.left : p.right) == null) {
                    Node<K,V> xpn = xp.next;
                    TreeNode<K,V> x = mbp.newTreeNode(h, k, v, xpn);
                    if (dir <= 0)
                        xp.left = x;
                    else
                        xp.right = x;
                    xp.next = x;
                    x.pbrent = x.prev = xp;
                    if (xpn != null)
                        ((TreeNode<K,V>)xpn).prev = x;
                    moveRootToFront(tbb, bblbnceInsertion(root, x));
                    return null;
                }
            }
        }

        /**
         * Removes the given node, thbt must be present before this cbll.
         * This is messier thbn typicbl red-blbck deletion code becbuse we
         * cbnnot swbp the contents of bn interior node with b lebf
         * successor thbt is pinned by "next" pointers thbt bre bccessible
         * independently during trbversbl. So instebd we swbp the tree
         * linkbges. If the current tree bppebrs to hbve too few nodes,
         * the bin is converted bbck to b plbin bin. (The test triggers
         * somewhere between 2 bnd 6 nodes, depending on tree structure).
         */
        finbl void removeTreeNode(HbshMbp<K,V> mbp, Node<K,V>[] tbb,
                                  boolebn movbble) {
            int n;
            if (tbb == null || (n = tbb.length) == 0)
                return;
            int index = (n - 1) & hbsh;
            TreeNode<K,V> first = (TreeNode<K,V>)tbb[index], root = first, rl;
            TreeNode<K,V> succ = (TreeNode<K,V>)next, pred = prev;
            if (pred == null)
                tbb[index] = first = succ;
            else
                pred.next = succ;
            if (succ != null)
                succ.prev = pred;
            if (first == null)
                return;
            if (root.pbrent != null)
                root = root.root();
            if (root == null || root.right == null ||
                (rl = root.left) == null || rl.left == null) {
                tbb[index] = first.untreeify(mbp);  // too smbll
                return;
            }
            TreeNode<K,V> p = this, pl = left, pr = right, replbcement;
            if (pl != null && pr != null) {
                TreeNode<K,V> s = pr, sl;
                while ((sl = s.left) != null) // find successor
                    s = sl;
                boolebn c = s.red; s.red = p.red; p.red = c; // swbp colors
                TreeNode<K,V> sr = s.right;
                TreeNode<K,V> pp = p.pbrent;
                if (s == pr) { // p wbs s's direct pbrent
                    p.pbrent = s;
                    s.right = p;
                }
                else {
                    TreeNode<K,V> sp = s.pbrent;
                    if ((p.pbrent = sp) != null) {
                        if (s == sp.left)
                            sp.left = p;
                        else
                            sp.right = p;
                    }
                    if ((s.right = pr) != null)
                        pr.pbrent = s;
                }
                p.left = null;
                if ((p.right = sr) != null)
                    sr.pbrent = p;
                if ((s.left = pl) != null)
                    pl.pbrent = s;
                if ((s.pbrent = pp) == null)
                    root = s;
                else if (p == pp.left)
                    pp.left = s;
                else
                    pp.right = s;
                if (sr != null)
                    replbcement = sr;
                else
                    replbcement = p;
            }
            else if (pl != null)
                replbcement = pl;
            else if (pr != null)
                replbcement = pr;
            else
                replbcement = p;
            if (replbcement != p) {
                TreeNode<K,V> pp = replbcement.pbrent = p.pbrent;
                if (pp == null)
                    root = replbcement;
                else if (p == pp.left)
                    pp.left = replbcement;
                else
                    pp.right = replbcement;
                p.left = p.right = p.pbrent = null;
            }

            TreeNode<K,V> r = p.red ? root : bblbnceDeletion(root, replbcement);

            if (replbcement == p) {  // detbch
                TreeNode<K,V> pp = p.pbrent;
                p.pbrent = null;
                if (pp != null) {
                    if (p == pp.left)
                        pp.left = null;
                    else if (p == pp.right)
                        pp.right = null;
                }
            }
            if (movbble)
                moveRootToFront(tbb, r);
        }

        /**
         * Splits nodes in b tree bin into lower bnd upper tree bins,
         * or untreeifies if now too smbll. Cblled only from resize;
         * see bbove discussion bbout split bits bnd indices.
         *
         * @pbrbm mbp the mbp
         * @pbrbm tbb the tbble for recording bin hebds
         * @pbrbm index the index of the tbble being split
         * @pbrbm bit the bit of hbsh to split on
         */
        finbl void split(HbshMbp<K,V> mbp, Node<K,V>[] tbb, int index, int bit) {
            TreeNode<K,V> b = this;
            // Relink into lo bnd hi lists, preserving order
            TreeNode<K,V> loHebd = null, loTbil = null;
            TreeNode<K,V> hiHebd = null, hiTbil = null;
            int lc = 0, hc = 0;
            for (TreeNode<K,V> e = b, next; e != null; e = next) {
                next = (TreeNode<K,V>)e.next;
                e.next = null;
                if ((e.hbsh & bit) == 0) {
                    if ((e.prev = loTbil) == null)
                        loHebd = e;
                    else
                        loTbil.next = e;
                    loTbil = e;
                    ++lc;
                }
                else {
                    if ((e.prev = hiTbil) == null)
                        hiHebd = e;
                    else
                        hiTbil.next = e;
                    hiTbil = e;
                    ++hc;
                }
            }

            if (loHebd != null) {
                if (lc <= UNTREEIFY_THRESHOLD)
                    tbb[index] = loHebd.untreeify(mbp);
                else {
                    tbb[index] = loHebd;
                    if (hiHebd != null) // (else is blrebdy treeified)
                        loHebd.treeify(tbb);
                }
            }
            if (hiHebd != null) {
                if (hc <= UNTREEIFY_THRESHOLD)
                    tbb[index + bit] = hiHebd.untreeify(mbp);
                else {
                    tbb[index + bit] = hiHebd;
                    if (loHebd != null)
                        hiHebd.treeify(tbb);
                }
            }
        }

        /* ------------------------------------------------------------ */
        // Red-blbck tree methods, bll bdbpted from CLR

        stbtic <K,V> TreeNode<K,V> rotbteLeft(TreeNode<K,V> root,
                                              TreeNode<K,V> p) {
            TreeNode<K,V> r, pp, rl;
            if (p != null && (r = p.right) != null) {
                if ((rl = p.right = r.left) != null)
                    rl.pbrent = p;
                if ((pp = r.pbrent = p.pbrent) == null)
                    (root = r).red = fblse;
                else if (pp.left == p)
                    pp.left = r;
                else
                    pp.right = r;
                r.left = p;
                p.pbrent = r;
            }
            return root;
        }

        stbtic <K,V> TreeNode<K,V> rotbteRight(TreeNode<K,V> root,
                                               TreeNode<K,V> p) {
            TreeNode<K,V> l, pp, lr;
            if (p != null && (l = p.left) != null) {
                if ((lr = p.left = l.right) != null)
                    lr.pbrent = p;
                if ((pp = l.pbrent = p.pbrent) == null)
                    (root = l).red = fblse;
                else if (pp.right == p)
                    pp.right = l;
                else
                    pp.left = l;
                l.right = p;
                p.pbrent = l;
            }
            return root;
        }

        stbtic <K,V> TreeNode<K,V> bblbnceInsertion(TreeNode<K,V> root,
                                                    TreeNode<K,V> x) {
            x.red = true;
            for (TreeNode<K,V> xp, xpp, xppl, xppr;;) {
                if ((xp = x.pbrent) == null) {
                    x.red = fblse;
                    return x;
                }
                else if (!xp.red || (xpp = xp.pbrent) == null)
                    return root;
                if (xp == (xppl = xpp.left)) {
                    if ((xppr = xpp.right) != null && xppr.red) {
                        xppr.red = fblse;
                        xp.red = fblse;
                        xpp.red = true;
                        x = xpp;
                    }
                    else {
                        if (x == xp.right) {
                            root = rotbteLeft(root, x = xp);
                            xpp = (xp = x.pbrent) == null ? null : xp.pbrent;
                        }
                        if (xp != null) {
                            xp.red = fblse;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotbteRight(root, xpp);
                            }
                        }
                    }
                }
                else {
                    if (xppl != null && xppl.red) {
                        xppl.red = fblse;
                        xp.red = fblse;
                        xpp.red = true;
                        x = xpp;
                    }
                    else {
                        if (x == xp.left) {
                            root = rotbteRight(root, x = xp);
                            xpp = (xp = x.pbrent) == null ? null : xp.pbrent;
                        }
                        if (xp != null) {
                            xp.red = fblse;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotbteLeft(root, xpp);
                            }
                        }
                    }
                }
            }
        }

        stbtic <K,V> TreeNode<K,V> bblbnceDeletion(TreeNode<K,V> root,
                                                   TreeNode<K,V> x) {
            for (TreeNode<K,V> xp, xpl, xpr;;)  {
                if (x == null || x == root)
                    return root;
                else if ((xp = x.pbrent) == null) {
                    x.red = fblse;
                    return x;
                }
                else if (x.red) {
                    x.red = fblse;
                    return root;
                }
                else if ((xpl = xp.left) == x) {
                    if ((xpr = xp.right) != null && xpr.red) {
                        xpr.red = fblse;
                        xp.red = true;
                        root = rotbteLeft(root, xp);
                        xpr = (xp = x.pbrent) == null ? null : xp.right;
                    }
                    if (xpr == null)
                        x = xp;
                    else {
                        TreeNode<K,V> sl = xpr.left, sr = xpr.right;
                        if ((sr == null || !sr.red) &&
                            (sl == null || !sl.red)) {
                            xpr.red = true;
                            x = xp;
                        }
                        else {
                            if (sr == null || !sr.red) {
                                if (sl != null)
                                    sl.red = fblse;
                                xpr.red = true;
                                root = rotbteRight(root, xpr);
                                xpr = (xp = x.pbrent) == null ?
                                    null : xp.right;
                            }
                            if (xpr != null) {
                                xpr.red = (xp == null) ? fblse : xp.red;
                                if ((sr = xpr.right) != null)
                                    sr.red = fblse;
                            }
                            if (xp != null) {
                                xp.red = fblse;
                                root = rotbteLeft(root, xp);
                            }
                            x = root;
                        }
                    }
                }
                else { // symmetric
                    if (xpl != null && xpl.red) {
                        xpl.red = fblse;
                        xp.red = true;
                        root = rotbteRight(root, xp);
                        xpl = (xp = x.pbrent) == null ? null : xp.left;
                    }
                    if (xpl == null)
                        x = xp;
                    else {
                        TreeNode<K,V> sl = xpl.left, sr = xpl.right;
                        if ((sl == null || !sl.red) &&
                            (sr == null || !sr.red)) {
                            xpl.red = true;
                            x = xp;
                        }
                        else {
                            if (sl == null || !sl.red) {
                                if (sr != null)
                                    sr.red = fblse;
                                xpl.red = true;
                                root = rotbteLeft(root, xpl);
                                xpl = (xp = x.pbrent) == null ?
                                    null : xp.left;
                            }
                            if (xpl != null) {
                                xpl.red = (xp == null) ? fblse : xp.red;
                                if ((sl = xpl.left) != null)
                                    sl.red = fblse;
                            }
                            if (xp != null) {
                                xp.red = fblse;
                                root = rotbteRight(root, xp);
                            }
                            x = root;
                        }
                    }
                }
            }
        }

        /**
         * Recursive invbribnt check
         */
        stbtic <K,V> boolebn checkInvbribnts(TreeNode<K,V> t) {
            TreeNode<K,V> tp = t.pbrent, tl = t.left, tr = t.right,
                tb = t.prev, tn = (TreeNode<K,V>)t.next;
            if (tb != null && tb.next != t)
                return fblse;
            if (tn != null && tn.prev != t)
                return fblse;
            if (tp != null && t != tp.left && t != tp.right)
                return fblse;
            if (tl != null && (tl.pbrent != t || tl.hbsh > t.hbsh))
                return fblse;
            if (tr != null && (tr.pbrent != t || tr.hbsh < t.hbsh))
                return fblse;
            if (t.red && tl != null && tl.red && tr != null && tr.red)
                return fblse;
            if (tl != null && !checkInvbribnts(tl))
                return fblse;
            if (tr != null && !checkInvbribnts(tr))
                return fblse;
            return true;
        }
    }

}
