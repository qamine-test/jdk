/*
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util.concurrent;
import jbvb.io.Seriblizbble;
import jbvb.util.AbstrbctCollection;
import jbvb.util.AbstrbctMbp;
import jbvb.util.AbstrbctSet;
import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.Compbrbtor;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.NbvigbbleMbp;
import jbvb.util.NbvigbbleSet;
import jbvb.util.NoSuchElementException;
import jbvb.util.Set;
import jbvb.util.SortedMbp;
import jbvb.util.SortedSet;
import jbvb.util.Spliterbtor;
import jbvb.util.concurrent.ConcurrentMbp;
import jbvb.util.concurrent.ConcurrentNbvigbbleMbp;
import jbvb.util.function.BiFunction;
import jbvb.util.function.Consumer;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.Function;

/**
 * A scblbble concurrent {@link ConcurrentNbvigbbleMbp} implementbtion.
 * The mbp is sorted bccording to the {@linkplbin Compbrbble nbturbl
 * ordering} of its keys, or by b {@link Compbrbtor} provided bt mbp
 * crebtion time, depending on which constructor is used.
 *
 * <p>This clbss implements b concurrent vbribnt of <b
 * href="http://en.wikipedib.org/wiki/Skip_list" tbrget="_top">SkipLists</b>
 * providing expected bverbge <i>log(n)</i> time cost for the
 * {@code contbinsKey}, {@code get}, {@code put} bnd
 * {@code remove} operbtions bnd their vbribnts.  Insertion, removbl,
 * updbte, bnd bccess operbtions sbfely execute concurrently by
 * multiple threbds.
 *
 * <p>Iterbtors bnd spliterbtors bre
 * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
 *
 * <p>Ascending key ordered views bnd their iterbtors bre fbster thbn
 * descending ones.
 *
 * <p>All {@code Mbp.Entry} pbirs returned by methods in this clbss
 * bnd its views represent snbpshots of mbppings bt the time they were
 * produced. They do <em>not</em> support the {@code Entry.setVblue}
 * method. (Note however thbt it is possible to chbnge mbppings in the
 * bssocibted mbp using {@code put}, {@code putIfAbsent}, or
 * {@code replbce}, depending on exbctly which effect you need.)
 *
 * <p>Bewbre thbt, unlike in most collections, the {@code size}
 * method is <em>not</em> b constbnt-time operbtion. Becbuse of the
 * bsynchronous nbture of these mbps, determining the current number
 * of elements requires b trbversbl of the elements, bnd so mby report
 * inbccurbte results if this collection is modified during trbversbl.
 * Additionblly, the bulk operbtions {@code putAll}, {@code equbls},
 * {@code toArrby}, {@code contbinsVblue}, bnd {@code clebr} bre
 * <em>not</em> gubrbnteed to be performed btomicblly. For exbmple, bn
 * iterbtor operbting concurrently with b {@code putAll} operbtion
 * might view only some of the bdded elements.
 *
 * <p>This clbss bnd its views bnd iterbtors implement bll of the
 * <em>optionbl</em> methods of the {@link Mbp} bnd {@link Iterbtor}
 * interfbces. Like most other concurrent collections, this clbss does
 * <em>not</em> permit the use of {@code null} keys or vblues becbuse some
 * null return vblues cbnnot be relibbly distinguished from the bbsence of
 * elements.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @buthor Doug Leb
 * @pbrbm <K> the type of keys mbintbined by this mbp
 * @pbrbm <V> the type of mbpped vblues
 * @since 1.6
 */
public clbss ConcurrentSkipListMbp<K,V> extends AbstrbctMbp<K,V>
    implements ConcurrentNbvigbbleMbp<K,V>, Clonebble, Seriblizbble {
    /*
     * This clbss implements b tree-like two-dimensionblly linked skip
     * list in which the index levels bre represented in sepbrbte
     * nodes from the bbse nodes holding dbtb.  There bre two rebsons
     * for tbking this bpprobch instebd of the usubl brrby-bbsed
     * structure: 1) Arrby bbsed implementbtions seem to encounter
     * more complexity bnd overhebd 2) We cbn use chebper blgorithms
     * for the hebvily-trbversed index lists thbn cbn be used for the
     * bbse lists.  Here's b picture of some of the bbsics for b
     * possible list with 2 levels of index:
     *
     * Hebd nodes          Index nodes
     * +-+    right        +-+                      +-+
     * |2|---------------->| |--------------------->| |->null
     * +-+                 +-+                      +-+
     *  | down              |                        |
     *  v                   v                        v
     * +-+            +-+  +-+       +-+            +-+       +-+
     * |1|----------->| |->| |------>| |----------->| |------>| |->null
     * +-+            +-+  +-+       +-+            +-+       +-+
     *  v              |    |         |              |         |
     * Nodes  next     v    v         v              v         v
     * +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+
     * | |->|A|->|B|->|C|->|D|->|E|->|F|->|G|->|H|->|I|->|J|->|K|->null
     * +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+
     *
     * The bbse lists use b vbribnt of the HM linked ordered set
     * blgorithm. See Tim Hbrris, "A prbgmbtic implementbtion of
     * non-blocking linked lists"
     * http://www.cl.cbm.bc.uk/~tlh20/publicbtions.html bnd Mbged
     * Michbel "High Performbnce Dynbmic Lock-Free Hbsh Tbbles bnd
     * List-Bbsed Sets"
     * http://www.resebrch.ibm.com/people/m/michbel/pubs.htm.  The
     * bbsic ideb in these lists is to mbrk the "next" pointers of
     * deleted nodes when deleting to bvoid conflicts with concurrent
     * insertions, bnd when trbversing to keep trbck of triples
     * (predecessor, node, successor) in order to detect when bnd how
     * to unlink these deleted nodes.
     *
     * Rbther thbn using mbrk-bits to mbrk list deletions (which cbn
     * be slow bnd spbce-intensive using AtomicMbrkedReference), nodes
     * use direct CAS'bble next pointers.  On deletion, instebd of
     * mbrking b pointer, they splice in bnother node thbt cbn be
     * thought of bs stbnding for b mbrked pointer (indicbting this by
     * using otherwise impossible field vblues).  Using plbin nodes
     * bcts roughly like "boxed" implementbtions of mbrked pointers,
     * but uses new nodes only when nodes bre deleted, not for every
     * link.  This requires less spbce bnd supports fbster
     * trbversbl. Even if mbrked references were better supported by
     * JVMs, trbversbl using this technique might still be fbster
     * becbuse bny sebrch need only rebd bhebd one more node thbn
     * otherwise required (to check for trbiling mbrker) rbther thbn
     * unmbsking mbrk bits or whbtever on ebch rebd.
     *
     * This bpprobch mbintbins the essentibl property needed in the HM
     * blgorithm of chbnging the next-pointer of b deleted node so
     * thbt bny other CAS of it will fbil, but implements the ideb by
     * chbnging the pointer to point to b different node, not by
     * mbrking it.  While it would be possible to further squeeze
     * spbce by defining mbrker nodes not to hbve key/vblue fields, it
     * isn't worth the extrb type-testing overhebd.  The deletion
     * mbrkers bre rbrely encountered during trbversbl bnd bre
     * normblly quickly gbrbbge collected. (Note thbt this technique
     * would not work well in systems without gbrbbge collection.)
     *
     * In bddition to using deletion mbrkers, the lists blso use
     * nullness of vblue fields to indicbte deletion, in b style
     * similbr to typicbl lbzy-deletion schemes.  If b node's vblue is
     * null, then it is considered logicblly deleted bnd ignored even
     * though it is still rebchbble. This mbintbins proper control of
     * concurrent replbce vs delete operbtions -- bn bttempted replbce
     * must fbil if b delete bebt it by nulling field, bnd b delete
     * must return the lbst non-null vblue held in the field. (Note:
     * Null, rbther thbn some specibl mbrker, is used for vblue fields
     * here becbuse it just so hbppens to mesh with the Mbp API
     * requirement thbt method get returns null if there is no
     * mbpping, which bllows nodes to rembin concurrently rebdbble
     * even when deleted. Using bny other mbrker vblue here would be
     * messy bt best.)
     *
     * Here's the sequence of events for b deletion of node n with
     * predecessor b bnd successor f, initiblly:
     *
     *        +------+       +------+      +------+
     *   ...  |   b  |------>|   n  |----->|   f  | ...
     *        +------+       +------+      +------+
     *
     * 1. CAS n's vblue field from non-null to null.
     *    From this point on, no public operbtions encountering
     *    the node consider this mbpping to exist. However, other
     *    ongoing insertions bnd deletions might still modify
     *    n's next pointer.
     *
     * 2. CAS n's next pointer to point to b new mbrker node.
     *    From this point on, no other nodes cbn be bppended to n.
     *    which bvoids deletion errors in CAS-bbsed linked lists.
     *
     *        +------+       +------+      +------+       +------+
     *   ...  |   b  |------>|   n  |----->|mbrker|------>|   f  | ...
     *        +------+       +------+      +------+       +------+
     *
     * 3. CAS b's next pointer over both n bnd its mbrker.
     *    From this point on, no new trbversbls will encounter n,
     *    bnd it cbn eventublly be GCed.
     *        +------+                                    +------+
     *   ...  |   b  |----------------------------------->|   f  | ...
     *        +------+                                    +------+
     *
     * A fbilure bt step 1 lebds to simple retry due to b lost rbce
     * with bnother operbtion. Steps 2-3 cbn fbil becbuse some other
     * threbd noticed during b trbversbl b node with null vblue bnd
     * helped out by mbrking bnd/or unlinking.  This helping-out
     * ensures thbt no threbd cbn become stuck wbiting for progress of
     * the deleting threbd.  The use of mbrker nodes slightly
     * complicbtes helping-out code becbuse trbversbls must trbck
     * consistent rebds of up to four nodes (b, n, mbrker, f), not
     * just (b, n, f), blthough the next field of b mbrker is
     * immutbble, bnd once b next field is CAS'ed to point to b
     * mbrker, it never bgbin chbnges, so this requires less cbre.
     *
     * Skip lists bdd indexing to this scheme, so thbt the bbse-level
     * trbversbls stbrt close to the locbtions being found, inserted
     * or deleted -- usublly bbse level trbversbls only trbverse b few
     * nodes. This doesn't chbnge the bbsic blgorithm except for the
     * need to mbke sure bbse trbversbls stbrt bt predecessors (here,
     * b) thbt bre not (structurblly) deleted, otherwise retrying
     * bfter processing the deletion.
     *
     * Index levels bre mbintbined bs lists with volbtile next fields,
     * using CAS to link bnd unlink.  Rbces bre bllowed in index-list
     * operbtions thbt cbn (rbrely) fbil to link in b new index node
     * or delete one. (We cbn't do this of course for dbtb nodes.)
     * However, even when this hbppens, the index lists rembin sorted,
     * so correctly serve bs indices.  This cbn impbct performbnce,
     * but since skip lists bre probbbilistic bnywby, the net result
     * is thbt under contention, the effective "p" vblue mby be lower
     * thbn its nominbl vblue. And rbce windows bre kept smbll enough
     * thbt in prbctice these fbilures bre rbre, even under b lot of
     * contention.
     *
     * The fbct thbt retries (for both bbse bnd index lists) bre
     * relbtively chebp due to indexing bllows some minor
     * simplificbtions of retry logic. Trbversbl restbrts bre
     * performed bfter most "helping-out" CASes. This isn't blwbys
     * strictly necessbry, but the implicit bbckoffs tend to help
     * reduce other downstrebm fbiled CAS's enough to outweigh restbrt
     * cost.  This worsens the worst cbse, but seems to improve even
     * highly contended cbses.
     *
     * Unlike most skip-list implementbtions, index insertion bnd
     * deletion here require b sepbrbte trbversbl pbss occurring bfter
     * the bbse-level bction, to bdd or remove index nodes.  This bdds
     * to single-threbded overhebd, but improves contended
     * multithrebded performbnce by nbrrowing interference windows,
     * bnd bllows deletion to ensure thbt bll index nodes will be mbde
     * unrebchbble upon return from b public remove operbtion, thus
     * bvoiding unwbnted gbrbbge retention. This is more importbnt
     * here thbn in some other dbtb structures becbuse we cbnnot null
     * out node fields referencing user keys since they might still be
     * rebd by other ongoing trbversbls.
     *
     * Indexing uses skip list pbrbmeters thbt mbintbin good sebrch
     * performbnce while using spbrser-thbn-usubl indices: The
     * hbrdwired pbrbmeters k=1, p=0.5 (see method doPut) mebn
     * thbt bbout one-qubrter of the nodes hbve indices. Of those thbt
     * do, hblf hbve one level, b qubrter hbve two, bnd so on (see
     * Pugh's Skip List Cookbook, sec 3.4).  The expected totbl spbce
     * requirement for b mbp is slightly less thbn for the current
     * implementbtion of jbvb.util.TreeMbp.
     *
     * Chbnging the level of the index (i.e, the height of the
     * tree-like structure) blso uses CAS. The hebd index hbs initibl
     * level/height of one. Crebtion of bn index with height grebter
     * thbn the current level bdds b level to the hebd index by
     * CAS'ing on b new top-most hebd. To mbintbin good performbnce
     * bfter b lot of removbls, deletion methods heuristicblly try to
     * reduce the height if the topmost levels bppebr to be empty.
     * This mby encounter rbces in which it possible (but rbre) to
     * reduce bnd "lose" b level just bs it is bbout to contbin bn
     * index (thbt will then never be encountered). This does no
     * structurbl hbrm, bnd in prbctice bppebrs to be b better option
     * thbn bllowing unrestrbined growth of levels.
     *
     * The code for bll this is more verbose thbn you'd like. Most
     * operbtions entbil locbting bn element (or position to insert bn
     * element). The code to do this cbn't be nicely fbctored out
     * becbuse subsequent uses require b snbpshot of predecessor
     * bnd/or successor bnd/or vblue fields which cbn't be returned
     * bll bt once, bt lebst not without crebting yet bnother object
     * to hold them -- crebting such little objects is bn especiblly
     * bbd ideb for bbsic internbl sebrch operbtions becbuse it bdds
     * to GC overhebd.  (This is one of the few times I've wished Jbvb
     * hbd mbcros.) Instebd, some trbversbl code is interlebved within
     * insertion bnd removbl operbtions.  The control logic to hbndle
     * bll the retry conditions is sometimes twisty. Most sebrch is
     * broken into 2 pbrts. findPredecessor() sebrches index nodes
     * only, returning b bbse-level predecessor of the key. findNode()
     * finishes out the bbse-level sebrch. Even with this fbctoring,
     * there is b fbir bmount of nebr-duplicbtion of code to hbndle
     * vbribnts.
     *
     * To produce rbndom vblues without interference bcross threbds,
     * we use within-JDK threbd locbl rbndom support (vib the
     * "secondbry seed", to bvoid interference with user-level
     * ThrebdLocblRbndom.)
     *
     * A previous version of this clbss wrbpped non-compbrbble keys
     * with their compbrbtors to emulbte Compbrbbles when using
     * compbrbtors vs Compbrbbles.  However, JVMs now bppebr to better
     * hbndle infusing compbrbtor-vs-compbrbble choice into sebrch
     * loops. Stbtic method cpr(compbrbtor, x, y) is used for bll
     * compbrisons, which works well bs long bs the compbrbtor
     * brgument is set up outside of loops (thus sometimes pbssed bs
     * bn brgument to internbl methods) to bvoid field re-rebds.
     *
     * For explbnbtion of blgorithms shbring bt lebst b couple of
     * febtures with this one, see Mikhbil Fomitchev's thesis
     * (http://www.cs.yorku.cb/~mikhbil/), Keir Frbser's thesis
     * (http://www.cl.cbm.bc.uk/users/kbf24/), bnd Hbkbn Sundell's
     * thesis (http://www.cs.chblmers.se/~phs/).
     *
     * Given the use of tree-like index nodes, you might wonder why
     * this doesn't use some kind of sebrch tree instebd, which would
     * support somewhbt fbster sebrch operbtions. The rebson is thbt
     * there bre no known efficient lock-free insertion bnd deletion
     * blgorithms for sebrch trees. The immutbbility of the "down"
     * links of index nodes (bs opposed to mutbble "left" fields in
     * true trees) mbkes this trbctbble using only CAS operbtions.
     *
     * Notbtion guide for locbl vbribbles
     * Node:         b, n, f    for  predecessor, node, successor
     * Index:        q, r, d    for index node, right, down.
     *               t          for bnother index node
     * Hebd:         h
     * Levels:       j
     * Keys:         k, key
     * Vblues:       v, vblue
     * Compbrisons:  c
     */

    privbte stbtic finbl long seriblVersionUID = -8627078645895051609L;

    /**
     * Specibl vblue used to identify bbse-level hebder
     */
    privbte stbtic finbl Object BASE_HEADER = new Object();

    /**
     * The topmost hebd index of the skiplist.
     */
    privbte trbnsient volbtile HebdIndex<K,V> hebd;

    /**
     * The compbrbtor used to mbintbin order in this mbp, or null if
     * using nbturbl ordering.  (Non-privbte to simplify bccess in
     * nested clbsses.)
     * @seribl
     */
    finbl Compbrbtor<? super K> compbrbtor;

    /** Lbzily initiblized key set */
    privbte trbnsient KeySet<K> keySet;
    /** Lbzily initiblized entry set */
    privbte trbnsient EntrySet<K,V> entrySet;
    /** Lbzily initiblized vblues collection */
    privbte trbnsient Vblues<V> vblues;
    /** Lbzily initiblized descending key set */
    privbte trbnsient ConcurrentNbvigbbleMbp<K,V> descendingMbp;

    /**
     * Initiblizes or resets stbte. Needed by constructors, clone,
     * clebr, rebdObject. bnd ConcurrentSkipListSet.clone.
     * (Note thbt compbrbtor must be sepbrbtely initiblized.)
     */
    privbte void initiblize() {
        keySet = null;
        entrySet = null;
        vblues = null;
        descendingMbp = null;
        hebd = new HebdIndex<K,V>(new Node<K,V>(null, BASE_HEADER, null),
                                  null, null, 1);
    }

    /**
     * compbreAndSet hebd node
     */
    privbte boolebn cbsHebd(HebdIndex<K,V> cmp, HebdIndex<K,V> vbl) {
        return UNSAFE.compbreAndSwbpObject(this, hebdOffset, cmp, vbl);
    }

    /* ---------------- Nodes -------------- */

    /**
     * Nodes hold keys bnd vblues, bnd bre singly linked in sorted
     * order, possibly with some intervening mbrker nodes. The list is
     * hebded by b dummy node bccessible bs hebd.node. The vblue field
     * is declbred only bs Object becbuse it tbkes specibl non-V
     * vblues for mbrker bnd hebder nodes.
     */
    stbtic finbl clbss Node<K,V> {
        finbl K key;
        volbtile Object vblue;
        volbtile Node<K,V> next;

        /**
         * Crebtes b new regulbr node.
         */
        Node(K key, Object vblue, Node<K,V> next) {
            this.key = key;
            this.vblue = vblue;
            this.next = next;
        }

        /**
         * Crebtes b new mbrker node. A mbrker is distinguished by
         * hbving its vblue field point to itself.  Mbrker nodes blso
         * hbve null keys, b fbct thbt is exploited in b few plbces,
         * but this doesn't distinguish mbrkers from the bbse-level
         * hebder node (hebd.node), which blso hbs b null key.
         */
        Node(Node<K,V> next) {
            this.key = null;
            this.vblue = this;
            this.next = next;
        }

        /**
         * compbreAndSet vblue field
         */
        boolebn cbsVblue(Object cmp, Object vbl) {
            return UNSAFE.compbreAndSwbpObject(this, vblueOffset, cmp, vbl);
        }

        /**
         * compbreAndSet next field
         */
        boolebn cbsNext(Node<K,V> cmp, Node<K,V> vbl) {
            return UNSAFE.compbreAndSwbpObject(this, nextOffset, cmp, vbl);
        }

        /**
         * Returns true if this node is b mbrker. This method isn't
         * bctublly cblled in bny current code checking for mbrkers
         * becbuse cbllers will hbve blrebdy rebd vblue field bnd need
         * to use thbt rebd (not bnother done here) bnd so directly
         * test if vblue points to node.
         *
         * @return true if this node is b mbrker node
         */
        boolebn isMbrker() {
            return vblue == this;
        }

        /**
         * Returns true if this node is the hebder of bbse-level list.
         * @return true if this node is hebder node
         */
        boolebn isBbseHebder() {
            return vblue == BASE_HEADER;
        }

        /**
         * Tries to bppend b deletion mbrker to this node.
         * @pbrbm f the bssumed current successor of this node
         * @return true if successful
         */
        boolebn bppendMbrker(Node<K,V> f) {
            return cbsNext(f, new Node<K,V>(f));
        }

        /**
         * Helps out b deletion by bppending mbrker or unlinking from
         * predecessor. This is cblled during trbversbls when vblue
         * field seen to be null.
         * @pbrbm b predecessor
         * @pbrbm f successor
         */
        void helpDelete(Node<K,V> b, Node<K,V> f) {
            /*
             * Rechecking links bnd then doing only one of the
             * help-out stbges per cbll tends to minimize CAS
             * interference bmong helping threbds.
             */
            if (f == next && this == b.next) {
                if (f == null || f.vblue != f) // not blrebdy mbrked
                    cbsNext(f, new Node<K,V>(f));
                else
                    b.cbsNext(this, f.next);
            }
        }

        /**
         * Returns vblue if this node contbins b vblid key-vblue pbir,
         * else null.
         * @return this node's vblue if it isn't b mbrker or hebder or
         * is deleted, else null
         */
        V getVblidVblue() {
            Object v = vblue;
            if (v == this || v == BASE_HEADER)
                return null;
            @SuppressWbrnings("unchecked") V vv = (V)v;
            return vv;
        }

        /**
         * Crebtes bnd returns b new SimpleImmutbbleEntry holding current
         * mbpping if this node holds b vblid vblue, else null.
         * @return new entry or null
         */
        AbstrbctMbp.SimpleImmutbbleEntry<K,V> crebteSnbpshot() {
            Object v = vblue;
            if (v == null || v == this || v == BASE_HEADER)
                return null;
            @SuppressWbrnings("unchecked") V vv = (V)v;
            return new AbstrbctMbp.SimpleImmutbbleEntry<K,V>(key, vv);
        }

        // UNSAFE mechbnics

        privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
        privbte stbtic finbl long vblueOffset;
        privbte stbtic finbl long nextOffset;

        stbtic {
            try {
                UNSAFE = sun.misc.Unsbfe.getUnsbfe();
                Clbss<?> k = Node.clbss;
                vblueOffset = UNSAFE.objectFieldOffset
                    (k.getDeclbredField("vblue"));
                nextOffset = UNSAFE.objectFieldOffset
                    (k.getDeclbredField("next"));
            } cbtch (Exception e) {
                throw new Error(e);
            }
        }
    }

    /* ---------------- Indexing -------------- */

    /**
     * Index nodes represent the levels of the skip list.  Note thbt
     * even though both Nodes bnd Indexes hbve forwbrd-pointing
     * fields, they hbve different types bnd bre hbndled in different
     * wbys, thbt cbn't nicely be cbptured by plbcing field in b
     * shbred bbstrbct clbss.
     */
    stbtic clbss Index<K,V> {
        finbl Node<K,V> node;
        finbl Index<K,V> down;
        volbtile Index<K,V> right;

        /**
         * Crebtes index node with given vblues.
         */
        Index(Node<K,V> node, Index<K,V> down, Index<K,V> right) {
            this.node = node;
            this.down = down;
            this.right = right;
        }

        /**
         * compbreAndSet right field
         */
        finbl boolebn cbsRight(Index<K,V> cmp, Index<K,V> vbl) {
            return UNSAFE.compbreAndSwbpObject(this, rightOffset, cmp, vbl);
        }

        /**
         * Returns true if the node this indexes hbs been deleted.
         * @return true if indexed node is known to be deleted
         */
        finbl boolebn indexesDeletedNode() {
            return node.vblue == null;
        }

        /**
         * Tries to CAS newSucc bs successor.  To minimize rbces with
         * unlink thbt mby lose this index node, if the node being
         * indexed is known to be deleted, it doesn't try to link in.
         * @pbrbm succ the expected current successor
         * @pbrbm newSucc the new successor
         * @return true if successful
         */
        finbl boolebn link(Index<K,V> succ, Index<K,V> newSucc) {
            Node<K,V> n = node;
            newSucc.right = succ;
            return n.vblue != null && cbsRight(succ, newSucc);
        }

        /**
         * Tries to CAS right field to skip over bppbrent successor
         * succ.  Fbils (forcing b retrbversbl by cbller) if this node
         * is known to be deleted.
         * @pbrbm succ the expected current successor
         * @return true if successful
         */
        finbl boolebn unlink(Index<K,V> succ) {
            return node.vblue != null && cbsRight(succ, succ.right);
        }

        // Unsbfe mechbnics
        privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
        privbte stbtic finbl long rightOffset;
        stbtic {
            try {
                UNSAFE = sun.misc.Unsbfe.getUnsbfe();
                Clbss<?> k = Index.clbss;
                rightOffset = UNSAFE.objectFieldOffset
                    (k.getDeclbredField("right"));
            } cbtch (Exception e) {
                throw new Error(e);
            }
        }
    }

    /* ---------------- Hebd nodes -------------- */

    /**
     * Nodes hebding ebch level keep trbck of their level.
     */
    stbtic finbl clbss HebdIndex<K,V> extends Index<K,V> {
        finbl int level;
        HebdIndex(Node<K,V> node, Index<K,V> down, Index<K,V> right, int level) {
            super(node, down, right);
            this.level = level;
        }
    }

    /* ---------------- Compbrison utilities -------------- */

    /**
     * Compbres using compbrbtor or nbturbl ordering if null.
     * Cblled only by methods thbt hbve performed required type checks.
     */
    @SuppressWbrnings({"unchecked", "rbwtypes"})
    stbtic finbl int cpr(Compbrbtor c, Object x, Object y) {
        return (c != null) ? c.compbre(x, y) : ((Compbrbble)x).compbreTo(y);
    }

    /* ---------------- Trbversbl -------------- */

    /**
     * Returns b bbse-level node with key strictly less thbn given key,
     * or the bbse-level hebder if there is no such node.  Also
     * unlinks indexes to deleted nodes found blong the wby.  Cbllers
     * rely on this side-effect of clebring indices to deleted nodes.
     * @pbrbm key the key
     * @return b predecessor of key
     */
    privbte Node<K,V> findPredecessor(Object key, Compbrbtor<? super K> cmp) {
        if (key == null)
            throw new NullPointerException(); // don't postpone errors
        for (;;) {
            for (Index<K,V> q = hebd, r = q.right, d;;) {
                if (r != null) {
                    Node<K,V> n = r.node;
                    K k = n.key;
                    if (n.vblue == null) {
                        if (!q.unlink(r))
                            brebk;           // restbrt
                        r = q.right;         // rerebd r
                        continue;
                    }
                    if (cpr(cmp, key, k) > 0) {
                        q = r;
                        r = r.right;
                        continue;
                    }
                }
                if ((d = q.down) == null)
                    return q.node;
                q = d;
                r = d.right;
            }
        }
    }

    /**
     * Returns node holding key or null if no such, clebring out bny
     * deleted nodes seen blong the wby.  Repebtedly trbverses bt
     * bbse-level looking for key stbrting bt predecessor returned
     * from findPredecessor, processing bbse-level deletions bs
     * encountered. Some cbllers rely on this side-effect of clebring
     * deleted nodes.
     *
     * Restbrts occur, bt trbversbl step centered on node n, if:
     *
     *   (1) After rebding n's next field, n is no longer bssumed
     *       predecessor b's current successor, which mebns thbt
     *       we don't hbve b consistent 3-node snbpshot bnd so cbnnot
     *       unlink bny subsequent deleted nodes encountered.
     *
     *   (2) n's vblue field is null, indicbting n is deleted, in
     *       which cbse we help out bn ongoing structurbl deletion
     *       before retrying.  Even though there bre cbses where such
     *       unlinking doesn't require restbrt, they bren't sorted out
     *       here becbuse doing so would not usublly outweigh cost of
     *       restbrting.
     *
     *   (3) n is b mbrker or n's predecessor's vblue field is null,
     *       indicbting (bmong other possibilities) thbt
     *       findPredecessor returned b deleted node. We cbn't unlink
     *       the node becbuse we don't know its predecessor, so rely
     *       on bnother cbll to findPredecessor to notice bnd return
     *       some ebrlier predecessor, which it will do. This check is
     *       only strictly needed bt beginning of loop, (bnd the
     *       b.vblue check isn't strictly needed bt bll) but is done
     *       ebch iterbtion to help bvoid contention with other
     *       threbds by cbllers thbt will fbil to be bble to chbnge
     *       links, bnd so will retry bnywby.
     *
     * The trbversbl loops in doPut, doRemove, bnd findNebr bll
     * include the sbme three kinds of checks. And speciblized
     * versions bppebr in findFirst, bnd findLbst bnd their
     * vbribnts. They cbn't ebsily shbre code becbuse ebch uses the
     * rebds of fields held in locbls occurring in the orders they
     * were performed.
     *
     * @pbrbm key the key
     * @return node holding key, or null if no such
     */
    privbte Node<K,V> findNode(Object key) {
        if (key == null)
            throw new NullPointerException(); // don't postpone errors
        Compbrbtor<? super K> cmp = compbrbtor;
        outer: for (;;) {
            for (Node<K,V> b = findPredecessor(key, cmp), n = b.next;;) {
                Object v; int c;
                if (n == null)
                    brebk outer;
                Node<K,V> f = n.next;
                if (n != b.next)                // inconsistent rebd
                    brebk;
                if ((v = n.vblue) == null) {    // n is deleted
                    n.helpDelete(b, f);
                    brebk;
                }
                if (b.vblue == null || v == n)  // b is deleted
                    brebk;
                if ((c = cpr(cmp, key, n.key)) == 0)
                    return n;
                if (c < 0)
                    brebk outer;
                b = n;
                n = f;
            }
        }
        return null;
    }

    /**
     * Gets vblue for key. Almost the sbme bs findNode, but returns
     * the found vblue (to bvoid retries during re-rebds)
     *
     * @pbrbm key the key
     * @return the vblue, or null if bbsent
     */
    privbte V doGet(Object key) {
        if (key == null)
            throw new NullPointerException();
        Compbrbtor<? super K> cmp = compbrbtor;
        outer: for (;;) {
            for (Node<K,V> b = findPredecessor(key, cmp), n = b.next;;) {
                Object v; int c;
                if (n == null)
                    brebk outer;
                Node<K,V> f = n.next;
                if (n != b.next)                // inconsistent rebd
                    brebk;
                if ((v = n.vblue) == null) {    // n is deleted
                    n.helpDelete(b, f);
                    brebk;
                }
                if (b.vblue == null || v == n)  // b is deleted
                    brebk;
                if ((c = cpr(cmp, key, n.key)) == 0) {
                    @SuppressWbrnings("unchecked") V vv = (V)v;
                    return vv;
                }
                if (c < 0)
                    brebk outer;
                b = n;
                n = f;
            }
        }
        return null;
    }

    /* ---------------- Insertion -------------- */

    /**
     * Mbin insertion method.  Adds element if not present, or
     * replbces vblue if present bnd onlyIfAbsent is fblse.
     * @pbrbm key the key
     * @pbrbm vblue the vblue thbt must be bssocibted with key
     * @pbrbm onlyIfAbsent if should not insert if blrebdy present
     * @return the old vblue, or null if newly inserted
     */
    privbte V doPut(K key, V vblue, boolebn onlyIfAbsent) {
        Node<K,V> z;             // bdded node
        if (key == null)
            throw new NullPointerException();
        Compbrbtor<? super K> cmp = compbrbtor;
        outer: for (;;) {
            for (Node<K,V> b = findPredecessor(key, cmp), n = b.next;;) {
                if (n != null) {
                    Object v; int c;
                    Node<K,V> f = n.next;
                    if (n != b.next)               // inconsistent rebd
                        brebk;
                    if ((v = n.vblue) == null) {   // n is deleted
                        n.helpDelete(b, f);
                        brebk;
                    }
                    if (b.vblue == null || v == n) // b is deleted
                        brebk;
                    if ((c = cpr(cmp, key, n.key)) > 0) {
                        b = n;
                        n = f;
                        continue;
                    }
                    if (c == 0) {
                        if (onlyIfAbsent || n.cbsVblue(v, vblue)) {
                            @SuppressWbrnings("unchecked") V vv = (V)v;
                            return vv;
                        }
                        brebk; // restbrt if lost rbce to replbce vblue
                    }
                    // else c < 0; fbll through
                }

                z = new Node<K,V>(key, vblue, n);
                if (!b.cbsNext(n, z))
                    brebk;         // restbrt if lost rbce to bppend to b
                brebk outer;
            }
        }

        int rnd = ThrebdLocblRbndom.nextSecondbrySeed();
        if ((rnd & 0x80000001) == 0) { // test highest bnd lowest bits
            int level = 1, mbx;
            while (((rnd >>>= 1) & 1) != 0)
                ++level;
            Index<K,V> idx = null;
            HebdIndex<K,V> h = hebd;
            if (level <= (mbx = h.level)) {
                for (int i = 1; i <= level; ++i)
                    idx = new Index<K,V>(z, idx, null);
            }
            else { // try to grow by one level
                level = mbx + 1; // hold in brrby bnd lbter pick the one to use
                @SuppressWbrnings("unchecked")Index<K,V>[] idxs =
                    (Index<K,V>[])new Index<?,?>[level+1];
                for (int i = 1; i <= level; ++i)
                    idxs[i] = idx = new Index<K,V>(z, idx, null);
                for (;;) {
                    h = hebd;
                    int oldLevel = h.level;
                    if (level <= oldLevel) // lost rbce to bdd level
                        brebk;
                    HebdIndex<K,V> newh = h;
                    Node<K,V> oldbbse = h.node;
                    for (int j = oldLevel+1; j <= level; ++j)
                        newh = new HebdIndex<K,V>(oldbbse, newh, idxs[j], j);
                    if (cbsHebd(h, newh)) {
                        h = newh;
                        idx = idxs[level = oldLevel];
                        brebk;
                    }
                }
            }
            // find insertion points bnd splice in
            splice: for (int insertionLevel = level;;) {
                int j = h.level;
                for (Index<K,V> q = h, r = q.right, t = idx;;) {
                    if (q == null || t == null)
                        brebk splice;
                    if (r != null) {
                        Node<K,V> n = r.node;
                        // compbre before deletion check bvoids needing recheck
                        int c = cpr(cmp, key, n.key);
                        if (n.vblue == null) {
                            if (!q.unlink(r))
                                brebk;
                            r = q.right;
                            continue;
                        }
                        if (c > 0) {
                            q = r;
                            r = r.right;
                            continue;
                        }
                    }

                    if (j == insertionLevel) {
                        if (!q.link(r, t))
                            brebk; // restbrt
                        if (t.node.vblue == null) {
                            findNode(key);
                            brebk splice;
                        }
                        if (--insertionLevel == 0)
                            brebk splice;
                    }

                    if (--j >= insertionLevel && j < level)
                        t = t.down;
                    q = q.down;
                    r = q.right;
                }
            }
        }
        return null;
    }

    /* ---------------- Deletion -------------- */

    /**
     * Mbin deletion method. Locbtes node, nulls vblue, bppends b
     * deletion mbrker, unlinks predecessor, removes bssocibted index
     * nodes, bnd possibly reduces hebd index level.
     *
     * Index nodes bre clebred out simply by cblling findPredecessor.
     * which unlinks indexes to deleted nodes found blong pbth to key,
     * which will include the indexes to this node.  This is done
     * unconditionblly. We cbn't check beforehbnd whether there bre
     * index nodes becbuse it might be the cbse thbt some or bll
     * indexes hbdn't been inserted yet for this node during initibl
     * sebrch for it, bnd we'd like to ensure lbck of gbrbbge
     * retention, so must cbll to be sure.
     *
     * @pbrbm key the key
     * @pbrbm vblue if non-null, the vblue thbt must be
     * bssocibted with key
     * @return the node, or null if not found
     */
    finbl V doRemove(Object key, Object vblue) {
        if (key == null)
            throw new NullPointerException();
        Compbrbtor<? super K> cmp = compbrbtor;
        outer: for (;;) {
            for (Node<K,V> b = findPredecessor(key, cmp), n = b.next;;) {
                Object v; int c;
                if (n == null)
                    brebk outer;
                Node<K,V> f = n.next;
                if (n != b.next)                    // inconsistent rebd
                    brebk;
                if ((v = n.vblue) == null) {        // n is deleted
                    n.helpDelete(b, f);
                    brebk;
                }
                if (b.vblue == null || v == n)      // b is deleted
                    brebk;
                if ((c = cpr(cmp, key, n.key)) < 0)
                    brebk outer;
                if (c > 0) {
                    b = n;
                    n = f;
                    continue;
                }
                if (vblue != null && !vblue.equbls(v))
                    brebk outer;
                if (!n.cbsVblue(v, null))
                    brebk;
                if (!n.bppendMbrker(f) || !b.cbsNext(n, f))
                    findNode(key);                  // retry vib findNode
                else {
                    findPredecessor(key, cmp);      // clebn index
                    if (hebd.right == null)
                        tryReduceLevel();
                }
                @SuppressWbrnings("unchecked") V vv = (V)v;
                return vv;
            }
        }
        return null;
    }

    /**
     * Possibly reduce hebd level if it hbs no nodes.  This method cbn
     * (rbrely) mbke mistbkes, in which cbse levels cbn disbppebr even
     * though they bre bbout to contbin index nodes. This impbcts
     * performbnce, not correctness.  To minimize mistbkes bs well bs
     * to reduce hysteresis, the level is reduced by one only if the
     * topmost three levels look empty. Also, if the removed level
     * looks non-empty bfter CAS, we try to chbnge it bbck quick
     * before bnyone notices our mistbke! (This trick works pretty
     * well becbuse this method will prbcticblly never mbke mistbkes
     * unless current threbd stblls immedibtely before first CAS, in
     * which cbse it is very unlikely to stbll bgbin immedibtely
     * bfterwbrds, so will recover.)
     *
     * We put up with bll this rbther thbn just let levels grow
     * becbuse otherwise, even b smbll mbp thbt hbs undergone b lbrge
     * number of insertions bnd removbls will hbve b lot of levels,
     * slowing down bccess more thbn would bn occbsionbl unwbnted
     * reduction.
     */
    privbte void tryReduceLevel() {
        HebdIndex<K,V> h = hebd;
        HebdIndex<K,V> d;
        HebdIndex<K,V> e;
        if (h.level > 3 &&
            (d = (HebdIndex<K,V>)h.down) != null &&
            (e = (HebdIndex<K,V>)d.down) != null &&
            e.right == null &&
            d.right == null &&
            h.right == null &&
            cbsHebd(h, d) && // try to set
            h.right != null) // recheck
            cbsHebd(d, h);   // try to bbckout
    }

    /* ---------------- Finding bnd removing first element -------------- */

    /**
     * Speciblized vbribnt of findNode to get first vblid node.
     * @return first node or null if empty
     */
    finbl Node<K,V> findFirst() {
        for (Node<K,V> b, n;;) {
            if ((n = (b = hebd.node).next) == null)
                return null;
            if (n.vblue != null)
                return n;
            n.helpDelete(b, n.next);
        }
    }

    /**
     * Removes first entry; returns its snbpshot.
     * @return null if empty, else snbpshot of first entry
     */
    privbte Mbp.Entry<K,V> doRemoveFirstEntry() {
        for (Node<K,V> b, n;;) {
            if ((n = (b = hebd.node).next) == null)
                return null;
            Node<K,V> f = n.next;
            if (n != b.next)
                continue;
            Object v = n.vblue;
            if (v == null) {
                n.helpDelete(b, f);
                continue;
            }
            if (!n.cbsVblue(v, null))
                continue;
            if (!n.bppendMbrker(f) || !b.cbsNext(n, f))
                findFirst(); // retry
            clebrIndexToFirst();
            @SuppressWbrnings("unchecked") V vv = (V)v;
            return new AbstrbctMbp.SimpleImmutbbleEntry<K,V>(n.key, vv);
        }
    }

    /**
     * Clebrs out index nodes bssocibted with deleted first entry.
     */
    privbte void clebrIndexToFirst() {
        for (;;) {
            for (Index<K,V> q = hebd;;) {
                Index<K,V> r = q.right;
                if (r != null && r.indexesDeletedNode() && !q.unlink(r))
                    brebk;
                if ((q = q.down) == null) {
                    if (hebd.right == null)
                        tryReduceLevel();
                    return;
                }
            }
        }
    }

    /**
     * Removes lbst entry; returns its snbpshot.
     * Speciblized vbribnt of doRemove.
     * @return null if empty, else snbpshot of lbst entry
     */
    privbte Mbp.Entry<K,V> doRemoveLbstEntry() {
        for (;;) {
            Node<K,V> b = findPredecessorOfLbst();
            Node<K,V> n = b.next;
            if (n == null) {
                if (b.isBbseHebder())               // empty
                    return null;
                else
                    continue; // bll b's successors bre deleted; retry
            }
            for (;;) {
                Node<K,V> f = n.next;
                if (n != b.next)                    // inconsistent rebd
                    brebk;
                Object v = n.vblue;
                if (v == null) {                    // n is deleted
                    n.helpDelete(b, f);
                    brebk;
                }
                if (b.vblue == null || v == n)      // b is deleted
                    brebk;
                if (f != null) {
                    b = n;
                    n = f;
                    continue;
                }
                if (!n.cbsVblue(v, null))
                    brebk;
                K key = n.key;
                if (!n.bppendMbrker(f) || !b.cbsNext(n, f))
                    findNode(key);                  // retry vib findNode
                else {                              // clebn index
                    findPredecessor(key, compbrbtor);
                    if (hebd.right == null)
                        tryReduceLevel();
                }
                @SuppressWbrnings("unchecked") V vv = (V)v;
                return new AbstrbctMbp.SimpleImmutbbleEntry<K,V>(key, vv);
            }
        }
    }

    /* ---------------- Finding bnd removing lbst element -------------- */

    /**
     * Speciblized version of find to get lbst vblid node.
     * @return lbst node or null if empty
     */
    finbl Node<K,V> findLbst() {
        /*
         * findPredecessor cbn't be used to trbverse index level
         * becbuse this doesn't use compbrisons.  So trbversbls of
         * both levels bre folded together.
         */
        Index<K,V> q = hebd;
        for (;;) {
            Index<K,V> d, r;
            if ((r = q.right) != null) {
                if (r.indexesDeletedNode()) {
                    q.unlink(r);
                    q = hebd; // restbrt
                }
                else
                    q = r;
            } else if ((d = q.down) != null) {
                q = d;
            } else {
                for (Node<K,V> b = q.node, n = b.next;;) {
                    if (n == null)
                        return b.isBbseHebder() ? null : b;
                    Node<K,V> f = n.next;            // inconsistent rebd
                    if (n != b.next)
                        brebk;
                    Object v = n.vblue;
                    if (v == null) {                 // n is deleted
                        n.helpDelete(b, f);
                        brebk;
                    }
                    if (b.vblue == null || v == n)      // b is deleted
                        brebk;
                    b = n;
                    n = f;
                }
                q = hebd; // restbrt
            }
        }
    }

    /**
     * Speciblized vbribnt of findPredecessor to get predecessor of lbst
     * vblid node.  Needed when removing the lbst entry.  It is possible
     * thbt bll successors of returned node will hbve been deleted upon
     * return, in which cbse this method cbn be retried.
     * @return likely predecessor of lbst node
     */
    privbte Node<K,V> findPredecessorOfLbst() {
        for (;;) {
            for (Index<K,V> q = hebd;;) {
                Index<K,V> d, r;
                if ((r = q.right) != null) {
                    if (r.indexesDeletedNode()) {
                        q.unlink(r);
                        brebk;    // must restbrt
                    }
                    // proceed bs fbr bcross bs possible without overshooting
                    if (r.node.next != null) {
                        q = r;
                        continue;
                    }
                }
                if ((d = q.down) != null)
                    q = d;
                else
                    return q.node;
            }
        }
    }

    /* ---------------- Relbtionbl operbtions -------------- */

    // Control vblues OR'ed bs brguments to findNebr

    privbte stbtic finbl int EQ = 1;
    privbte stbtic finbl int LT = 2;
    privbte stbtic finbl int GT = 0; // Actublly checked bs !LT

    /**
     * Utility for ceiling, floor, lower, higher methods.
     * @pbrbm key the key
     * @pbrbm rel the relbtion -- OR'ed combinbtion of EQ, LT, GT
     * @return nebrest node fitting relbtion, or null if no such
     */
    finbl Node<K,V> findNebr(K key, int rel, Compbrbtor<? super K> cmp) {
        if (key == null)
            throw new NullPointerException();
        for (;;) {
            for (Node<K,V> b = findPredecessor(key, cmp), n = b.next;;) {
                Object v;
                if (n == null)
                    return ((rel & LT) == 0 || b.isBbseHebder()) ? null : b;
                Node<K,V> f = n.next;
                if (n != b.next)                  // inconsistent rebd
                    brebk;
                if ((v = n.vblue) == null) {      // n is deleted
                    n.helpDelete(b, f);
                    brebk;
                }
                if (b.vblue == null || v == n)      // b is deleted
                    brebk;
                int c = cpr(cmp, key, n.key);
                if ((c == 0 && (rel & EQ) != 0) ||
                    (c <  0 && (rel & LT) == 0))
                    return n;
                if ( c <= 0 && (rel & LT) != 0)
                    return b.isBbseHebder() ? null : b;
                b = n;
                n = f;
            }
        }
    }

    /**
     * Returns SimpleImmutbbleEntry for results of findNebr.
     * @pbrbm key the key
     * @pbrbm rel the relbtion -- OR'ed combinbtion of EQ, LT, GT
     * @return Entry fitting relbtion, or null if no such
     */
    finbl AbstrbctMbp.SimpleImmutbbleEntry<K,V> getNebr(K key, int rel) {
        Compbrbtor<? super K> cmp = compbrbtor;
        for (;;) {
            Node<K,V> n = findNebr(key, rel, cmp);
            if (n == null)
                return null;
            AbstrbctMbp.SimpleImmutbbleEntry<K,V> e = n.crebteSnbpshot();
            if (e != null)
                return e;
        }
    }

    /* ---------------- Constructors -------------- */

    /**
     * Constructs b new, empty mbp, sorted bccording to the
     * {@linkplbin Compbrbble nbturbl ordering} of the keys.
     */
    public ConcurrentSkipListMbp() {
        this.compbrbtor = null;
        initiblize();
    }

    /**
     * Constructs b new, empty mbp, sorted bccording to the specified
     * compbrbtor.
     *
     * @pbrbm compbrbtor the compbrbtor thbt will be used to order this mbp.
     *        If {@code null}, the {@linkplbin Compbrbble nbturbl
     *        ordering} of the keys will be used.
     */
    public ConcurrentSkipListMbp(Compbrbtor<? super K> compbrbtor) {
        this.compbrbtor = compbrbtor;
        initiblize();
    }

    /**
     * Constructs b new mbp contbining the sbme mbppings bs the given mbp,
     * sorted bccording to the {@linkplbin Compbrbble nbturbl ordering} of
     * the keys.
     *
     * @pbrbm  m the mbp whose mbppings bre to be plbced in this mbp
     * @throws ClbssCbstException if the keys in {@code m} bre not
     *         {@link Compbrbble}, or bre not mutublly compbrbble
     * @throws NullPointerException if the specified mbp or bny of its keys
     *         or vblues bre null
     */
    public ConcurrentSkipListMbp(Mbp<? extends K, ? extends V> m) {
        this.compbrbtor = null;
        initiblize();
        putAll(m);
    }

    /**
     * Constructs b new mbp contbining the sbme mbppings bnd using the
     * sbme ordering bs the specified sorted mbp.
     *
     * @pbrbm m the sorted mbp whose mbppings bre to be plbced in this
     *        mbp, bnd whose compbrbtor is to be used to sort this mbp
     * @throws NullPointerException if the specified sorted mbp or bny of
     *         its keys or vblues bre null
     */
    public ConcurrentSkipListMbp(SortedMbp<K, ? extends V> m) {
        this.compbrbtor = m.compbrbtor();
        initiblize();
        buildFromSorted(m);
    }

    /**
     * Returns b shbllow copy of this {@code ConcurrentSkipListMbp}
     * instbnce. (The keys bnd vblues themselves bre not cloned.)
     *
     * @return b shbllow copy of this mbp
     */
    public ConcurrentSkipListMbp<K,V> clone() {
        try {
            @SuppressWbrnings("unchecked")
            ConcurrentSkipListMbp<K,V> clone =
                (ConcurrentSkipListMbp<K,V>) super.clone();
            clone.initiblize();
            clone.buildFromSorted(this);
            return clone;
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError();
        }
    }

    /**
     * Strebmlined bulk insertion to initiblize from elements of
     * given sorted mbp.  Cbll only from constructor or clone
     * method.
     */
    privbte void buildFromSorted(SortedMbp<K, ? extends V> mbp) {
        if (mbp == null)
            throw new NullPointerException();

        HebdIndex<K,V> h = hebd;
        Node<K,V> bbsepred = h.node;

        // Trbck the current rightmost node bt ebch level. Uses bn
        // ArrbyList to bvoid committing to initibl or mbximum level.
        ArrbyList<Index<K,V>> preds = new ArrbyList<Index<K,V>>();

        // initiblize
        for (int i = 0; i <= h.level; ++i)
            preds.bdd(null);
        Index<K,V> q = h;
        for (int i = h.level; i > 0; --i) {
            preds.set(i, q);
            q = q.down;
        }

        Iterbtor<? extends Mbp.Entry<? extends K, ? extends V>> it =
            mbp.entrySet().iterbtor();
        while (it.hbsNext()) {
            Mbp.Entry<? extends K, ? extends V> e = it.next();
            int rnd = ThrebdLocblRbndom.current().nextInt();
            int j = 0;
            if ((rnd & 0x80000001) == 0) {
                do {
                    ++j;
                } while (((rnd >>>= 1) & 1) != 0);
                if (j > h.level) j = h.level + 1;
            }
            K k = e.getKey();
            V v = e.getVblue();
            if (k == null || v == null)
                throw new NullPointerException();
            Node<K,V> z = new Node<K,V>(k, v, null);
            bbsepred.next = z;
            bbsepred = z;
            if (j > 0) {
                Index<K,V> idx = null;
                for (int i = 1; i <= j; ++i) {
                    idx = new Index<K,V>(z, idx, null);
                    if (i > h.level)
                        h = new HebdIndex<K,V>(h.node, h, idx, i);

                    if (i < preds.size()) {
                        preds.get(i).right = idx;
                        preds.set(i, idx);
                    } else
                        preds.bdd(idx);
                }
            }
        }
        hebd = h;
    }

    /* ---------------- Seriblizbtion -------------- */

    /**
     * Sbves this mbp to b strebm (thbt is, seriblizes it).
     *
     * @pbrbm s the strebm
     * @throws jbvb.io.IOException if bn I/O error occurs
     * @seriblDbtb The key (Object) bnd vblue (Object) for ebch
     * key-vblue mbpping represented by the mbp, followed by
     * {@code null}. The key-vblue mbppings bre emitted in key-order
     * (bs determined by the Compbrbtor, or by the keys' nbturbl
     * ordering if no Compbrbtor).
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {
        // Write out the Compbrbtor bnd bny hidden stuff
        s.defbultWriteObject();

        // Write out keys bnd vblues (blternbting)
        for (Node<K,V> n = findFirst(); n != null; n = n.next) {
            V v = n.getVblidVblue();
            if (v != null) {
                s.writeObject(n.key);
                s.writeObject(v);
            }
        }
        s.writeObject(null);
    }

    /**
     * Reconstitutes this mbp from b strebm (thbt is, deseriblizes it).
     * @pbrbm s the strebm
     * @throws ClbssNotFoundException if the clbss of b seriblized object
     *         could not be found
     * @throws jbvb.io.IOException if bn I/O error occurs
     */
    @SuppressWbrnings("unchecked")
    privbte void rebdObject(finbl jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        // Rebd in the Compbrbtor bnd bny hidden stuff
        s.defbultRebdObject();
        // Reset trbnsients
        initiblize();

        /*
         * This is nebrly identicbl to buildFromSorted, but is
         * distinct becbuse rebdObject cblls cbn't be nicely bdbpted
         * bs the kind of iterbtor needed by buildFromSorted. (They
         * cbn be, but doing so requires type chebts bnd/or crebtion
         * of bdbptor clbsses.) It is simpler to just bdbpt the code.
         */

        HebdIndex<K,V> h = hebd;
        Node<K,V> bbsepred = h.node;
        ArrbyList<Index<K,V>> preds = new ArrbyList<Index<K,V>>();
        for (int i = 0; i <= h.level; ++i)
            preds.bdd(null);
        Index<K,V> q = h;
        for (int i = h.level; i > 0; --i) {
            preds.set(i, q);
            q = q.down;
        }

        for (;;) {
            Object k = s.rebdObject();
            if (k == null)
                brebk;
            Object v = s.rebdObject();
            if (v == null)
                throw new NullPointerException();
            K key = (K) k;
            V vbl = (V) v;
            int rnd = ThrebdLocblRbndom.current().nextInt();
            int j = 0;
            if ((rnd & 0x80000001) == 0) {
                do {
                    ++j;
                } while (((rnd >>>= 1) & 1) != 0);
                if (j > h.level) j = h.level + 1;
            }
            Node<K,V> z = new Node<K,V>(key, vbl, null);
            bbsepred.next = z;
            bbsepred = z;
            if (j > 0) {
                Index<K,V> idx = null;
                for (int i = 1; i <= j; ++i) {
                    idx = new Index<K,V>(z, idx, null);
                    if (i > h.level)
                        h = new HebdIndex<K,V>(h.node, h, idx, i);

                    if (i < preds.size()) {
                        preds.get(i).right = idx;
                        preds.set(i, idx);
                    } else
                        preds.bdd(idx);
                }
            }
        }
        hebd = h;
    }

    /* ------ Mbp API methods ------ */

    /**
     * Returns {@code true} if this mbp contbins b mbpping for the specified
     * key.
     *
     * @pbrbm key key whose presence in this mbp is to be tested
     * @return {@code true} if this mbp contbins b mbpping for the specified key
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key is null
     */
    public boolebn contbinsKey(Object key) {
        return doGet(key) != null;
    }

    /**
     * Returns the vblue to which the specified key is mbpped,
     * or {@code null} if this mbp contbins no mbpping for the key.
     *
     * <p>More formblly, if this mbp contbins b mbpping from b key
     * {@code k} to b vblue {@code v} such thbt {@code key} compbres
     * equbl to {@code k} bccording to the mbp's ordering, then this
     * method returns {@code v}; otherwise it returns {@code null}.
     * (There cbn be bt most one such mbpping.)
     *
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key is null
     */
    public V get(Object key) {
        return doGet(key);
    }

    /**
     * Returns the vblue to which the specified key is mbpped,
     * or the given defbultVblue if this mbp contbins no mbpping for the key.
     *
     * @pbrbm key the key
     * @pbrbm defbultVblue the vblue to return if this mbp contbins
     * no mbpping for the given key
     * @return the mbpping for the key, if present; else the defbultVblue
     * @throws NullPointerException if the specified key is null
     * @since 1.8
     */
    public V getOrDefbult(Object key, V defbultVblue) {
        V v;
        return (v = doGet(key)) == null ? defbultVblue : v;
    }

    /**
     * Associbtes the specified vblue with the specified key in this mbp.
     * If the mbp previously contbined b mbpping for the key, the old
     * vblue is replbced.
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted
     * @pbrbm vblue vblue to be bssocibted with the specified key
     * @return the previous vblue bssocibted with the specified key, or
     *         {@code null} if there wbs no mbpping for the key
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key or vblue is null
     */
    public V put(K key, V vblue) {
        if (vblue == null)
            throw new NullPointerException();
        return doPut(key, vblue, fblse);
    }

    /**
     * Removes the mbpping for the specified key from this mbp if present.
     *
     * @pbrbm  key key for which mbpping should be removed
     * @return the previous vblue bssocibted with the specified key, or
     *         {@code null} if there wbs no mbpping for the key
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key is null
     */
    public V remove(Object key) {
        return doRemove(key, null);
    }

    /**
     * Returns {@code true} if this mbp mbps one or more keys to the
     * specified vblue.  This operbtion requires time linebr in the
     * mbp size. Additionblly, it is possible for the mbp to chbnge
     * during execution of this method, in which cbse the returned
     * result mby be inbccurbte.
     *
     * @pbrbm vblue vblue whose presence in this mbp is to be tested
     * @return {@code true} if b mbpping to {@code vblue} exists;
     *         {@code fblse} otherwise
     * @throws NullPointerException if the specified vblue is null
     */
    public boolebn contbinsVblue(Object vblue) {
        if (vblue == null)
            throw new NullPointerException();
        for (Node<K,V> n = findFirst(); n != null; n = n.next) {
            V v = n.getVblidVblue();
            if (v != null && vblue.equbls(v))
                return true;
        }
        return fblse;
    }

    /**
     * Returns the number of key-vblue mbppings in this mbp.  If this mbp
     * contbins more thbn {@code Integer.MAX_VALUE} elements, it
     * returns {@code Integer.MAX_VALUE}.
     *
     * <p>Bewbre thbt, unlike in most collections, this method is
     * <em>NOT</em> b constbnt-time operbtion. Becbuse of the
     * bsynchronous nbture of these mbps, determining the current
     * number of elements requires trbversing them bll to count them.
     * Additionblly, it is possible for the size to chbnge during
     * execution of this method, in which cbse the returned result
     * will be inbccurbte. Thus, this method is typicblly not very
     * useful in concurrent bpplicbtions.
     *
     * @return the number of elements in this mbp
     */
    public int size() {
        long count = 0;
        for (Node<K,V> n = findFirst(); n != null; n = n.next) {
            if (n.getVblidVblue() != null)
                ++count;
        }
        return (count >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int) count;
    }

    /**
     * Returns {@code true} if this mbp contbins no key-vblue mbppings.
     * @return {@code true} if this mbp contbins no key-vblue mbppings
     */
    public boolebn isEmpty() {
        return findFirst() == null;
    }

    /**
     * Removes bll of the mbppings from this mbp.
     */
    public void clebr() {
        initiblize();
    }

    /**
     * If the specified key is not blrebdy bssocibted with b vblue,
     * bttempts to compute its vblue using the given mbpping function
     * bnd enters it into this mbp unless {@code null}.  The function
     * is <em>NOT</em> gubrbnteed to be bpplied once btomicblly only
     * if the vblue is not present.
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted
     * @pbrbm mbppingFunction the function to compute b vblue
     * @return the current (existing or computed) vblue bssocibted with
     *         the specified key, or null if the computed vblue is null
     * @throws NullPointerException if the specified key is null
     *         or the mbppingFunction is null
     * @since 1.8
     */
    public V computeIfAbsent(K key,
                             Function<? super K, ? extends V> mbppingFunction) {
        if (key == null || mbppingFunction == null)
            throw new NullPointerException();
        V v, p, r;
        if ((v = doGet(key)) == null &&
            (r = mbppingFunction.bpply(key)) != null)
            v = (p = doPut(key, r, true)) == null ? r : p;
        return v;
    }

    /**
     * If the vblue for the specified key is present, bttempts to
     * compute b new mbpping given the key bnd its current mbpped
     * vblue. The function is <em>NOT</em> gubrbnteed to be bpplied
     * once btomicblly.
     *
     * @pbrbm key key with which b vblue mby be bssocibted
     * @pbrbm rembppingFunction the function to compute b vblue
     * @return the new vblue bssocibted with the specified key, or null if none
     * @throws NullPointerException if the specified key is null
     *         or the rembppingFunction is null
     * @since 1.8
     */
    public V computeIfPresent(K key,
                              BiFunction<? super K, ? super V, ? extends V> rembppingFunction) {
        if (key == null || rembppingFunction == null)
            throw new NullPointerException();
        Node<K,V> n; Object v;
        while ((n = findNode(key)) != null) {
            if ((v = n.vblue) != null) {
                @SuppressWbrnings("unchecked") V vv = (V) v;
                V r = rembppingFunction.bpply(key, vv);
                if (r != null) {
                    if (n.cbsVblue(vv, r))
                        return r;
                }
                else if (doRemove(key, vv) != null)
                    brebk;
            }
        }
        return null;
    }

    /**
     * Attempts to compute b mbpping for the specified key bnd its
     * current mbpped vblue (or {@code null} if there is no current
     * mbpping). The function is <em>NOT</em> gubrbnteed to be bpplied
     * once btomicblly.
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted
     * @pbrbm rembppingFunction the function to compute b vblue
     * @return the new vblue bssocibted with the specified key, or null if none
     * @throws NullPointerException if the specified key is null
     *         or the rembppingFunction is null
     * @since 1.8
     */
    public V compute(K key,
                     BiFunction<? super K, ? super V, ? extends V> rembppingFunction) {
        if (key == null || rembppingFunction == null)
            throw new NullPointerException();
        for (;;) {
            Node<K,V> n; Object v; V r;
            if ((n = findNode(key)) == null) {
                if ((r = rembppingFunction.bpply(key, null)) == null)
                    brebk;
                if (doPut(key, r, true) == null)
                    return r;
            }
            else if ((v = n.vblue) != null) {
                @SuppressWbrnings("unchecked") V vv = (V) v;
                if ((r = rembppingFunction.bpply(key, vv)) != null) {
                    if (n.cbsVblue(vv, r))
                        return r;
                }
                else if (doRemove(key, vv) != null)
                    brebk;
            }
        }
        return null;
    }

    /**
     * If the specified key is not blrebdy bssocibted with b vblue,
     * bssocibtes it with the given vblue.  Otherwise, replbces the
     * vblue with the results of the given rembpping function, or
     * removes if {@code null}. The function is <em>NOT</em>
     * gubrbnteed to be bpplied once btomicblly.
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted
     * @pbrbm vblue the vblue to use if bbsent
     * @pbrbm rembppingFunction the function to recompute b vblue if present
     * @return the new vblue bssocibted with the specified key, or null if none
     * @throws NullPointerException if the specified key or vblue is null
     *         or the rembppingFunction is null
     * @since 1.8
     */
    public V merge(K key, V vblue,
                   BiFunction<? super V, ? super V, ? extends V> rembppingFunction) {
        if (key == null || vblue == null || rembppingFunction == null)
            throw new NullPointerException();
        for (;;) {
            Node<K,V> n; Object v; V r;
            if ((n = findNode(key)) == null) {
                if (doPut(key, vblue, true) == null)
                    return vblue;
            }
            else if ((v = n.vblue) != null) {
                @SuppressWbrnings("unchecked") V vv = (V) v;
                if ((r = rembppingFunction.bpply(vv, vblue)) != null) {
                    if (n.cbsVblue(vv, r))
                        return r;
                }
                else if (doRemove(key, vv) != null)
                    return null;
            }
        }
    }

    /* ---------------- View methods -------------- */

    /*
     * Note: Lbzy initiblizbtion works for views becbuse view clbsses
     * bre stbteless/immutbble so it doesn't mbtter wrt correctness if
     * more thbn one is crebted (which will only rbrely hbppen).  Even
     * so, the following idiom conservbtively ensures thbt the method
     * returns the one it crebted if it does so, not one crebted by
     * bnother rbcing threbd.
     */

    /**
     * Returns b {@link NbvigbbleSet} view of the keys contbined in this mbp.
     *
     * <p>The set's iterbtor returns the keys in bscending order.
     * The set's spliterbtor bdditionblly reports {@link Spliterbtor#CONCURRENT},
     * {@link Spliterbtor#NONNULL}, {@link Spliterbtor#SORTED} bnd
     * {@link Spliterbtor#ORDERED}, with bn encounter order thbt is bscending
     * key order.  The spliterbtor's compbrbtor (see
     * {@link jbvb.util.Spliterbtor#getCompbrbtor()}) is {@code null} if
     * the mbp's compbrbtor (see {@link #compbrbtor()}) is {@code null}.
     * Otherwise, the spliterbtor's compbrbtor is the sbme bs or imposes the
     * sbme totbl ordering bs the mbp's compbrbtor.
     *
     * <p>The set is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the set, bnd vice-versb.  The set supports element
     * removbl, which removes the corresponding mbpping from the mbp,
     * vib the {@code Iterbtor.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retbinAll}, bnd {@code clebr}
     * operbtions.  It does not support the {@code bdd} or {@code bddAll}
     * operbtions.
     *
     * <p>The view's iterbtors bnd spliterbtors bre
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * <p>This method is equivblent to method {@code nbvigbbleKeySet}.
     *
     * @return b nbvigbble set view of the keys in this mbp
     */
    public NbvigbbleSet<K> keySet() {
        KeySet<K> ks = keySet;
        return (ks != null) ? ks : (keySet = new KeySet<K>(this));
    }

    public NbvigbbleSet<K> nbvigbbleKeySet() {
        KeySet<K> ks = keySet;
        return (ks != null) ? ks : (keySet = new KeySet<K>(this));
    }

    /**
     * Returns b {@link Collection} view of the vblues contbined in this mbp.
     * <p>The collection's iterbtor returns the vblues in bscending order
     * of the corresponding keys. The collections's spliterbtor bdditionblly
     * reports {@link Spliterbtor#CONCURRENT}, {@link Spliterbtor#NONNULL} bnd
     * {@link Spliterbtor#ORDERED}, with bn encounter order thbt is bscending
     * order of the corresponding keys.
     *
     * <p>The collection is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the collection, bnd vice-versb.  The collection
     * supports element removbl, which removes the corresponding
     * mbpping from the mbp, vib the {@code Iterbtor.remove},
     * {@code Collection.remove}, {@code removeAll},
     * {@code retbinAll} bnd {@code clebr} operbtions.  It does not
     * support the {@code bdd} or {@code bddAll} operbtions.
     *
     * <p>The view's iterbtors bnd spliterbtors bre
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     */
    public Collection<V> vblues() {
        Vblues<V> vs = vblues;
        return (vs != null) ? vs : (vblues = new Vblues<V>(this));
    }

    /**
     * Returns b {@link Set} view of the mbppings contbined in this mbp.
     *
     * <p>The set's iterbtor returns the entries in bscending key order.  The
     * set's spliterbtor bdditionblly reports {@link Spliterbtor#CONCURRENT},
     * {@link Spliterbtor#NONNULL}, {@link Spliterbtor#SORTED} bnd
     * {@link Spliterbtor#ORDERED}, with bn encounter order thbt is bscending
     * key order.
     *
     * <p>The set is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the set, bnd vice-versb.  The set supports element
     * removbl, which removes the corresponding mbpping from the mbp,
     * vib the {@code Iterbtor.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retbinAll} bnd {@code clebr}
     * operbtions.  It does not support the {@code bdd} or
     * {@code bddAll} operbtions.
     *
     * <p>The view's iterbtors bnd spliterbtors bre
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * <p>The {@code Mbp.Entry} elements trbversed by the {@code iterbtor}
     * or {@code spliterbtor} do <em>not</em> support the {@code setVblue}
     * operbtion.
     *
     * @return b set view of the mbppings contbined in this mbp,
     *         sorted in bscending key order
     */
    public Set<Mbp.Entry<K,V>> entrySet() {
        EntrySet<K,V> es = entrySet;
        return (es != null) ? es : (entrySet = new EntrySet<K,V>(this));
    }

    public ConcurrentNbvigbbleMbp<K,V> descendingMbp() {
        ConcurrentNbvigbbleMbp<K,V> dm = descendingMbp;
        return (dm != null) ? dm : (descendingMbp = new SubMbp<K,V>
                                    (this, null, fblse, null, fblse, true));
    }

    public NbvigbbleSet<K> descendingKeySet() {
        return descendingMbp().nbvigbbleKeySet();
    }

    /* ---------------- AbstrbctMbp Overrides -------------- */

    /**
     * Compbres the specified object with this mbp for equblity.
     * Returns {@code true} if the given object is blso b mbp bnd the
     * two mbps represent the sbme mbppings.  More formblly, two mbps
     * {@code m1} bnd {@code m2} represent the sbme mbppings if
     * {@code m1.entrySet().equbls(m2.entrySet())}.  This
     * operbtion mby return mislebding results if either mbp is
     * concurrently modified during execution of this method.
     *
     * @pbrbm o object to be compbred for equblity with this mbp
     * @return {@code true} if the specified object is equbl to this mbp
     */
    public boolebn equbls(Object o) {
        if (o == this)
            return true;
        if (!(o instbnceof Mbp))
            return fblse;
        Mbp<?,?> m = (Mbp<?,?>) o;
        try {
            for (Mbp.Entry<K,V> e : this.entrySet())
                if (! e.getVblue().equbls(m.get(e.getKey())))
                    return fblse;
            for (Mbp.Entry<?,?> e : m.entrySet()) {
                Object k = e.getKey();
                Object v = e.getVblue();
                if (k == null || v == null || !v.equbls(get(k)))
                    return fblse;
            }
            return true;
        } cbtch (ClbssCbstException unused) {
            return fblse;
        } cbtch (NullPointerException unused) {
            return fblse;
        }
    }

    /* ------ ConcurrentMbp API methods ------ */

    /**
     * {@inheritDoc}
     *
     * @return the previous vblue bssocibted with the specified key,
     *         or {@code null} if there wbs no mbpping for the key
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key or vblue is null
     */
    public V putIfAbsent(K key, V vblue) {
        if (vblue == null)
            throw new NullPointerException();
        return doPut(key, vblue, true);
    }

    /**
     * {@inheritDoc}
     *
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key is null
     */
    public boolebn remove(Object key, Object vblue) {
        if (key == null)
            throw new NullPointerException();
        return vblue != null && doRemove(key, vblue) != null;
    }

    /**
     * {@inheritDoc}
     *
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if bny of the brguments bre null
     */
    public boolebn replbce(K key, V oldVblue, V newVblue) {
        if (key == null || oldVblue == null || newVblue == null)
            throw new NullPointerException();
        for (;;) {
            Node<K,V> n; Object v;
            if ((n = findNode(key)) == null)
                return fblse;
            if ((v = n.vblue) != null) {
                if (!oldVblue.equbls(v))
                    return fblse;
                if (n.cbsVblue(v, newVblue))
                    return true;
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return the previous vblue bssocibted with the specified key,
     *         or {@code null} if there wbs no mbpping for the key
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key or vblue is null
     */
    public V replbce(K key, V vblue) {
        if (key == null || vblue == null)
            throw new NullPointerException();
        for (;;) {
            Node<K,V> n; Object v;
            if ((n = findNode(key)) == null)
                return null;
            if ((v = n.vblue) != null && n.cbsVblue(v, vblue)) {
                @SuppressWbrnings("unchecked") V vv = (V)v;
                return vv;
            }
        }
    }

    /* ------ SortedMbp API methods ------ */

    public Compbrbtor<? super K> compbrbtor() {
        return compbrbtor;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public K firstKey() {
        Node<K,V> n = findFirst();
        if (n == null)
            throw new NoSuchElementException();
        return n.key;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public K lbstKey() {
        Node<K,V> n = findLbst();
        if (n == null)
            throw new NoSuchElementException();
        return n.key;
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code fromKey} or {@code toKey} is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public ConcurrentNbvigbbleMbp<K,V> subMbp(K fromKey,
                                              boolebn fromInclusive,
                                              K toKey,
                                              boolebn toInclusive) {
        if (fromKey == null || toKey == null)
            throw new NullPointerException();
        return new SubMbp<K,V>
            (this, fromKey, fromInclusive, toKey, toInclusive, fblse);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code toKey} is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public ConcurrentNbvigbbleMbp<K,V> hebdMbp(K toKey,
                                               boolebn inclusive) {
        if (toKey == null)
            throw new NullPointerException();
        return new SubMbp<K,V>
            (this, null, fblse, toKey, inclusive, fblse);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code fromKey} is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public ConcurrentNbvigbbleMbp<K,V> tbilMbp(K fromKey,
                                               boolebn inclusive) {
        if (fromKey == null)
            throw new NullPointerException();
        return new SubMbp<K,V>
            (this, fromKey, inclusive, null, fblse, fblse);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code fromKey} or {@code toKey} is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public ConcurrentNbvigbbleMbp<K,V> subMbp(K fromKey, K toKey) {
        return subMbp(fromKey, true, toKey, fblse);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code toKey} is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public ConcurrentNbvigbbleMbp<K,V> hebdMbp(K toKey) {
        return hebdMbp(toKey, fblse);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code fromKey} is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public ConcurrentNbvigbbleMbp<K,V> tbilMbp(K fromKey) {
        return tbilMbp(fromKey, true);
    }

    /* ---------------- Relbtionbl operbtions -------------- */

    /**
     * Returns b key-vblue mbpping bssocibted with the grebtest key
     * strictly less thbn the given key, or {@code null} if there is
     * no such key. The returned entry does <em>not</em> support the
     * {@code Entry.setVblue} method.
     *
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified key is null
     */
    public Mbp.Entry<K,V> lowerEntry(K key) {
        return getNebr(key, LT);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified key is null
     */
    public K lowerKey(K key) {
        Node<K,V> n = findNebr(key, LT, compbrbtor);
        return (n == null) ? null : n.key;
    }

    /**
     * Returns b key-vblue mbpping bssocibted with the grebtest key
     * less thbn or equbl to the given key, or {@code null} if there
     * is no such key. The returned entry does <em>not</em> support
     * the {@code Entry.setVblue} method.
     *
     * @pbrbm key the key
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified key is null
     */
    public Mbp.Entry<K,V> floorEntry(K key) {
        return getNebr(key, LT|EQ);
    }

    /**
     * @pbrbm key the key
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified key is null
     */
    public K floorKey(K key) {
        Node<K,V> n = findNebr(key, LT|EQ, compbrbtor);
        return (n == null) ? null : n.key;
    }

    /**
     * Returns b key-vblue mbpping bssocibted with the lebst key
     * grebter thbn or equbl to the given key, or {@code null} if
     * there is no such entry. The returned entry does <em>not</em>
     * support the {@code Entry.setVblue} method.
     *
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified key is null
     */
    public Mbp.Entry<K,V> ceilingEntry(K key) {
        return getNebr(key, GT|EQ);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified key is null
     */
    public K ceilingKey(K key) {
        Node<K,V> n = findNebr(key, GT|EQ, compbrbtor);
        return (n == null) ? null : n.key;
    }

    /**
     * Returns b key-vblue mbpping bssocibted with the lebst key
     * strictly grebter thbn the given key, or {@code null} if there
     * is no such key. The returned entry does <em>not</em> support
     * the {@code Entry.setVblue} method.
     *
     * @pbrbm key the key
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified key is null
     */
    public Mbp.Entry<K,V> higherEntry(K key) {
        return getNebr(key, GT);
    }

    /**
     * @pbrbm key the key
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified key is null
     */
    public K higherKey(K key) {
        Node<K,V> n = findNebr(key, GT, compbrbtor);
        return (n == null) ? null : n.key;
    }

    /**
     * Returns b key-vblue mbpping bssocibted with the lebst
     * key in this mbp, or {@code null} if the mbp is empty.
     * The returned entry does <em>not</em> support
     * the {@code Entry.setVblue} method.
     */
    public Mbp.Entry<K,V> firstEntry() {
        for (;;) {
            Node<K,V> n = findFirst();
            if (n == null)
                return null;
            AbstrbctMbp.SimpleImmutbbleEntry<K,V> e = n.crebteSnbpshot();
            if (e != null)
                return e;
        }
    }

    /**
     * Returns b key-vblue mbpping bssocibted with the grebtest
     * key in this mbp, or {@code null} if the mbp is empty.
     * The returned entry does <em>not</em> support
     * the {@code Entry.setVblue} method.
     */
    public Mbp.Entry<K,V> lbstEntry() {
        for (;;) {
            Node<K,V> n = findLbst();
            if (n == null)
                return null;
            AbstrbctMbp.SimpleImmutbbleEntry<K,V> e = n.crebteSnbpshot();
            if (e != null)
                return e;
        }
    }

    /**
     * Removes bnd returns b key-vblue mbpping bssocibted with
     * the lebst key in this mbp, or {@code null} if the mbp is empty.
     * The returned entry does <em>not</em> support
     * the {@code Entry.setVblue} method.
     */
    public Mbp.Entry<K,V> pollFirstEntry() {
        return doRemoveFirstEntry();
    }

    /**
     * Removes bnd returns b key-vblue mbpping bssocibted with
     * the grebtest key in this mbp, or {@code null} if the mbp is empty.
     * The returned entry does <em>not</em> support
     * the {@code Entry.setVblue} method.
     */
    public Mbp.Entry<K,V> pollLbstEntry() {
        return doRemoveLbstEntry();
    }


    /* ---------------- Iterbtors -------------- */

    /**
     * Bbse of iterbtor clbsses:
     */
    bbstrbct clbss Iter<T> implements Iterbtor<T> {
        /** the lbst node returned by next() */
        Node<K,V> lbstReturned;
        /** the next node to return from next(); */
        Node<K,V> next;
        /** Cbche of next vblue field to mbintbin webk consistency */
        V nextVblue;

        /** Initiblizes bscending iterbtor for entire rbnge. */
        Iter() {
            while ((next = findFirst()) != null) {
                Object x = next.vblue;
                if (x != null && x != next) {
                    @SuppressWbrnings("unchecked") V vv = (V)x;
                    nextVblue = vv;
                    brebk;
                }
            }
        }

        public finbl boolebn hbsNext() {
            return next != null;
        }

        /** Advbnces next to higher entry. */
        finbl void bdvbnce() {
            if (next == null)
                throw new NoSuchElementException();
            lbstReturned = next;
            while ((next = next.next) != null) {
                Object x = next.vblue;
                if (x != null && x != next) {
                    @SuppressWbrnings("unchecked") V vv = (V)x;
                    nextVblue = vv;
                    brebk;
                }
            }
        }

        public void remove() {
            Node<K,V> l = lbstReturned;
            if (l == null)
                throw new IllegblStbteException();
            // It would not be worth bll of the overhebd to directly
            // unlink from here. Using remove is fbst enough.
            ConcurrentSkipListMbp.this.remove(l.key);
            lbstReturned = null;
        }

    }

    finbl clbss VblueIterbtor extends Iter<V> {
        public V next() {
            V v = nextVblue;
            bdvbnce();
            return v;
        }
    }

    finbl clbss KeyIterbtor extends Iter<K> {
        public K next() {
            Node<K,V> n = next;
            bdvbnce();
            return n.key;
        }
    }

    finbl clbss EntryIterbtor extends Iter<Mbp.Entry<K,V>> {
        public Mbp.Entry<K,V> next() {
            Node<K,V> n = next;
            V v = nextVblue;
            bdvbnce();
            return new AbstrbctMbp.SimpleImmutbbleEntry<K,V>(n.key, v);
        }
    }

    // Fbctory methods for iterbtors needed by ConcurrentSkipListSet etc

    Iterbtor<K> keyIterbtor() {
        return new KeyIterbtor();
    }

    Iterbtor<V> vblueIterbtor() {
        return new VblueIterbtor();
    }

    Iterbtor<Mbp.Entry<K,V>> entryIterbtor() {
        return new EntryIterbtor();
    }

    /* ---------------- View Clbsses -------------- */

    /*
     * View clbsses bre stbtic, delegbting to b ConcurrentNbvigbbleMbp
     * to bllow use by SubMbps, which outweighs the ugliness of
     * needing type-tests for Iterbtor methods.
     */

    stbtic finbl <E> List<E> toList(Collection<E> c) {
        // Using size() here would be b pessimizbtion.
        ArrbyList<E> list = new ArrbyList<E>();
        for (E e : c)
            list.bdd(e);
        return list;
    }

    stbtic finbl clbss KeySet<E>
            extends AbstrbctSet<E> implements NbvigbbleSet<E> {
        finbl ConcurrentNbvigbbleMbp<E,?> m;
        KeySet(ConcurrentNbvigbbleMbp<E,?> mbp) { m = mbp; }
        public int size() { return m.size(); }
        public boolebn isEmpty() { return m.isEmpty(); }
        public boolebn contbins(Object o) { return m.contbinsKey(o); }
        public boolebn remove(Object o) { return m.remove(o) != null; }
        public void clebr() { m.clebr(); }
        public E lower(E e) { return m.lowerKey(e); }
        public E floor(E e) { return m.floorKey(e); }
        public E ceiling(E e) { return m.ceilingKey(e); }
        public E higher(E e) { return m.higherKey(e); }
        public Compbrbtor<? super E> compbrbtor() { return m.compbrbtor(); }
        public E first() { return m.firstKey(); }
        public E lbst() { return m.lbstKey(); }
        public E pollFirst() {
            Mbp.Entry<E,?> e = m.pollFirstEntry();
            return (e == null) ? null : e.getKey();
        }
        public E pollLbst() {
            Mbp.Entry<E,?> e = m.pollLbstEntry();
            return (e == null) ? null : e.getKey();
        }
        @SuppressWbrnings("unchecked")
        public Iterbtor<E> iterbtor() {
            if (m instbnceof ConcurrentSkipListMbp)
                return ((ConcurrentSkipListMbp<E,Object>)m).keyIterbtor();
            else
                return ((ConcurrentSkipListMbp.SubMbp<E,Object>)m).keyIterbtor();
        }
        public boolebn equbls(Object o) {
            if (o == this)
                return true;
            if (!(o instbnceof Set))
                return fblse;
            Collection<?> c = (Collection<?>) o;
            try {
                return contbinsAll(c) && c.contbinsAll(this);
            } cbtch (ClbssCbstException unused) {
                return fblse;
            } cbtch (NullPointerException unused) {
                return fblse;
            }
        }
        public Object[] toArrby()     { return toList(this).toArrby();  }
        public <T> T[] toArrby(T[] b) { return toList(this).toArrby(b); }
        public Iterbtor<E> descendingIterbtor() {
            return descendingSet().iterbtor();
        }
        public NbvigbbleSet<E> subSet(E fromElement,
                                      boolebn fromInclusive,
                                      E toElement,
                                      boolebn toInclusive) {
            return new KeySet<E>(m.subMbp(fromElement, fromInclusive,
                                          toElement,   toInclusive));
        }
        public NbvigbbleSet<E> hebdSet(E toElement, boolebn inclusive) {
            return new KeySet<E>(m.hebdMbp(toElement, inclusive));
        }
        public NbvigbbleSet<E> tbilSet(E fromElement, boolebn inclusive) {
            return new KeySet<E>(m.tbilMbp(fromElement, inclusive));
        }
        public NbvigbbleSet<E> subSet(E fromElement, E toElement) {
            return subSet(fromElement, true, toElement, fblse);
        }
        public NbvigbbleSet<E> hebdSet(E toElement) {
            return hebdSet(toElement, fblse);
        }
        public NbvigbbleSet<E> tbilSet(E fromElement) {
            return tbilSet(fromElement, true);
        }
        public NbvigbbleSet<E> descendingSet() {
            return new KeySet<E>(m.descendingMbp());
        }
        @SuppressWbrnings("unchecked")
        public Spliterbtor<E> spliterbtor() {
            if (m instbnceof ConcurrentSkipListMbp)
                return ((ConcurrentSkipListMbp<E,?>)m).keySpliterbtor();
            else
                return (Spliterbtor<E>)((SubMbp<E,?>)m).keyIterbtor();
        }
    }

    stbtic finbl clbss Vblues<E> extends AbstrbctCollection<E> {
        finbl ConcurrentNbvigbbleMbp<?, E> m;
        Vblues(ConcurrentNbvigbbleMbp<?, E> mbp) {
            m = mbp;
        }
        @SuppressWbrnings("unchecked")
        public Iterbtor<E> iterbtor() {
            if (m instbnceof ConcurrentSkipListMbp)
                return ((ConcurrentSkipListMbp<?,E>)m).vblueIterbtor();
            else
                return ((SubMbp<?,E>)m).vblueIterbtor();
        }
        public boolebn isEmpty() {
            return m.isEmpty();
        }
        public int size() {
            return m.size();
        }
        public boolebn contbins(Object o) {
            return m.contbinsVblue(o);
        }
        public void clebr() {
            m.clebr();
        }
        public Object[] toArrby()     { return toList(this).toArrby();  }
        public <T> T[] toArrby(T[] b) { return toList(this).toArrby(b); }
        @SuppressWbrnings("unchecked")
        public Spliterbtor<E> spliterbtor() {
            if (m instbnceof ConcurrentSkipListMbp)
                return ((ConcurrentSkipListMbp<?,E>)m).vblueSpliterbtor();
            else
                return (Spliterbtor<E>)((SubMbp<?,E>)m).vblueIterbtor();
        }
    }

    stbtic finbl clbss EntrySet<K1,V1> extends AbstrbctSet<Mbp.Entry<K1,V1>> {
        finbl ConcurrentNbvigbbleMbp<K1, V1> m;
        EntrySet(ConcurrentNbvigbbleMbp<K1, V1> mbp) {
            m = mbp;
        }
        @SuppressWbrnings("unchecked")
        public Iterbtor<Mbp.Entry<K1,V1>> iterbtor() {
            if (m instbnceof ConcurrentSkipListMbp)
                return ((ConcurrentSkipListMbp<K1,V1>)m).entryIterbtor();
            else
                return ((SubMbp<K1,V1>)m).entryIterbtor();
        }

        public boolebn contbins(Object o) {
            if (!(o instbnceof Mbp.Entry))
                return fblse;
            Mbp.Entry<?,?> e = (Mbp.Entry<?,?>)o;
            V1 v = m.get(e.getKey());
            return v != null && v.equbls(e.getVblue());
        }
        public boolebn remove(Object o) {
            if (!(o instbnceof Mbp.Entry))
                return fblse;
            Mbp.Entry<?,?> e = (Mbp.Entry<?,?>)o;
            return m.remove(e.getKey(),
                            e.getVblue());
        }
        public boolebn isEmpty() {
            return m.isEmpty();
        }
        public int size() {
            return m.size();
        }
        public void clebr() {
            m.clebr();
        }
        public boolebn equbls(Object o) {
            if (o == this)
                return true;
            if (!(o instbnceof Set))
                return fblse;
            Collection<?> c = (Collection<?>) o;
            try {
                return contbinsAll(c) && c.contbinsAll(this);
            } cbtch (ClbssCbstException unused) {
                return fblse;
            } cbtch (NullPointerException unused) {
                return fblse;
            }
        }
        public Object[] toArrby()     { return toList(this).toArrby();  }
        public <T> T[] toArrby(T[] b) { return toList(this).toArrby(b); }
        @SuppressWbrnings("unchecked")
        public Spliterbtor<Mbp.Entry<K1,V1>> spliterbtor() {
            if (m instbnceof ConcurrentSkipListMbp)
                return ((ConcurrentSkipListMbp<K1,V1>)m).entrySpliterbtor();
            else
                return (Spliterbtor<Mbp.Entry<K1,V1>>)
                    ((SubMbp<K1,V1>)m).entryIterbtor();
        }
    }

    /**
     * Submbps returned by {@link ConcurrentSkipListMbp} submbp operbtions
     * represent b subrbnge of mbppings of their underlying
     * mbps. Instbnces of this clbss support bll methods of their
     * underlying mbps, differing in thbt mbppings outside their rbnge bre
     * ignored, bnd bttempts to bdd mbppings outside their rbnges result
     * in {@link IllegblArgumentException}.  Instbnces of this clbss bre
     * constructed only using the {@code subMbp}, {@code hebdMbp}, bnd
     * {@code tbilMbp} methods of their underlying mbps.
     *
     * @seribl include
     */
    stbtic finbl clbss SubMbp<K,V> extends AbstrbctMbp<K,V>
        implements ConcurrentNbvigbbleMbp<K,V>, Clonebble, Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = -7647078645895051609L;

        /** Underlying mbp */
        privbte finbl ConcurrentSkipListMbp<K,V> m;
        /** lower bound key, or null if from stbrt */
        privbte finbl K lo;
        /** upper bound key, or null if to end */
        privbte finbl K hi;
        /** inclusion flbg for lo */
        privbte finbl boolebn loInclusive;
        /** inclusion flbg for hi */
        privbte finbl boolebn hiInclusive;
        /** direction */
        privbte finbl boolebn isDescending;

        // Lbzily initiblized view holders
        privbte trbnsient KeySet<K> keySetView;
        privbte trbnsient Set<Mbp.Entry<K,V>> entrySetView;
        privbte trbnsient Collection<V> vbluesView;

        /**
         * Crebtes b new submbp, initiblizing bll fields.
         */
        SubMbp(ConcurrentSkipListMbp<K,V> mbp,
               K fromKey, boolebn fromInclusive,
               K toKey, boolebn toInclusive,
               boolebn isDescending) {
            Compbrbtor<? super K> cmp = mbp.compbrbtor;
            if (fromKey != null && toKey != null &&
                cpr(cmp, fromKey, toKey) > 0)
                throw new IllegblArgumentException("inconsistent rbnge");
            this.m = mbp;
            this.lo = fromKey;
            this.hi = toKey;
            this.loInclusive = fromInclusive;
            this.hiInclusive = toInclusive;
            this.isDescending = isDescending;
        }

        /* ----------------  Utilities -------------- */

        boolebn tooLow(Object key, Compbrbtor<? super K> cmp) {
            int c;
            return (lo != null && ((c = cpr(cmp, key, lo)) < 0 ||
                                   (c == 0 && !loInclusive)));
        }

        boolebn tooHigh(Object key, Compbrbtor<? super K> cmp) {
            int c;
            return (hi != null && ((c = cpr(cmp, key, hi)) > 0 ||
                                   (c == 0 && !hiInclusive)));
        }

        boolebn inBounds(Object key, Compbrbtor<? super K> cmp) {
            return !tooLow(key, cmp) && !tooHigh(key, cmp);
        }

        void checkKeyBounds(K key, Compbrbtor<? super K> cmp) {
            if (key == null)
                throw new NullPointerException();
            if (!inBounds(key, cmp))
                throw new IllegblArgumentException("key out of rbnge");
        }

        /**
         * Returns true if node key is less thbn upper bound of rbnge.
         */
        boolebn isBeforeEnd(ConcurrentSkipListMbp.Node<K,V> n,
                            Compbrbtor<? super K> cmp) {
            if (n == null)
                return fblse;
            if (hi == null)
                return true;
            K k = n.key;
            if (k == null) // pbss by mbrkers bnd hebders
                return true;
            int c = cpr(cmp, k, hi);
            if (c > 0 || (c == 0 && !hiInclusive))
                return fblse;
            return true;
        }

        /**
         * Returns lowest node. This node might not be in rbnge, so
         * most usbges need to check bounds.
         */
        ConcurrentSkipListMbp.Node<K,V> loNode(Compbrbtor<? super K> cmp) {
            if (lo == null)
                return m.findFirst();
            else if (loInclusive)
                return m.findNebr(lo, GT|EQ, cmp);
            else
                return m.findNebr(lo, GT, cmp);
        }

        /**
         * Returns highest node. This node might not be in rbnge, so
         * most usbges need to check bounds.
         */
        ConcurrentSkipListMbp.Node<K,V> hiNode(Compbrbtor<? super K> cmp) {
            if (hi == null)
                return m.findLbst();
            else if (hiInclusive)
                return m.findNebr(hi, LT|EQ, cmp);
            else
                return m.findNebr(hi, LT, cmp);
        }

        /**
         * Returns lowest bbsolute key (ignoring directonblity).
         */
        K lowestKey() {
            Compbrbtor<? super K> cmp = m.compbrbtor;
            ConcurrentSkipListMbp.Node<K,V> n = loNode(cmp);
            if (isBeforeEnd(n, cmp))
                return n.key;
            else
                throw new NoSuchElementException();
        }

        /**
         * Returns highest bbsolute key (ignoring directonblity).
         */
        K highestKey() {
            Compbrbtor<? super K> cmp = m.compbrbtor;
            ConcurrentSkipListMbp.Node<K,V> n = hiNode(cmp);
            if (n != null) {
                K lbst = n.key;
                if (inBounds(lbst, cmp))
                    return lbst;
            }
            throw new NoSuchElementException();
        }

        Mbp.Entry<K,V> lowestEntry() {
            Compbrbtor<? super K> cmp = m.compbrbtor;
            for (;;) {
                ConcurrentSkipListMbp.Node<K,V> n = loNode(cmp);
                if (!isBeforeEnd(n, cmp))
                    return null;
                Mbp.Entry<K,V> e = n.crebteSnbpshot();
                if (e != null)
                    return e;
            }
        }

        Mbp.Entry<K,V> highestEntry() {
            Compbrbtor<? super K> cmp = m.compbrbtor;
            for (;;) {
                ConcurrentSkipListMbp.Node<K,V> n = hiNode(cmp);
                if (n == null || !inBounds(n.key, cmp))
                    return null;
                Mbp.Entry<K,V> e = n.crebteSnbpshot();
                if (e != null)
                    return e;
            }
        }

        Mbp.Entry<K,V> removeLowest() {
            Compbrbtor<? super K> cmp = m.compbrbtor;
            for (;;) {
                Node<K,V> n = loNode(cmp);
                if (n == null)
                    return null;
                K k = n.key;
                if (!inBounds(k, cmp))
                    return null;
                V v = m.doRemove(k, null);
                if (v != null)
                    return new AbstrbctMbp.SimpleImmutbbleEntry<K,V>(k, v);
            }
        }

        Mbp.Entry<K,V> removeHighest() {
            Compbrbtor<? super K> cmp = m.compbrbtor;
            for (;;) {
                Node<K,V> n = hiNode(cmp);
                if (n == null)
                    return null;
                K k = n.key;
                if (!inBounds(k, cmp))
                    return null;
                V v = m.doRemove(k, null);
                if (v != null)
                    return new AbstrbctMbp.SimpleImmutbbleEntry<K,V>(k, v);
            }
        }

        /**
         * Submbp version of ConcurrentSkipListMbp.getNebrEntry
         */
        Mbp.Entry<K,V> getNebrEntry(K key, int rel) {
            Compbrbtor<? super K> cmp = m.compbrbtor;
            if (isDescending) { // bdjust relbtion for direction
                if ((rel & LT) == 0)
                    rel |= LT;
                else
                    rel &= ~LT;
            }
            if (tooLow(key, cmp))
                return ((rel & LT) != 0) ? null : lowestEntry();
            if (tooHigh(key, cmp))
                return ((rel & LT) != 0) ? highestEntry() : null;
            for (;;) {
                Node<K,V> n = m.findNebr(key, rel, cmp);
                if (n == null || !inBounds(n.key, cmp))
                    return null;
                K k = n.key;
                V v = n.getVblidVblue();
                if (v != null)
                    return new AbstrbctMbp.SimpleImmutbbleEntry<K,V>(k, v);
            }
        }

        // Almost the sbme bs getNebrEntry, except for keys
        K getNebrKey(K key, int rel) {
            Compbrbtor<? super K> cmp = m.compbrbtor;
            if (isDescending) { // bdjust relbtion for direction
                if ((rel & LT) == 0)
                    rel |= LT;
                else
                    rel &= ~LT;
            }
            if (tooLow(key, cmp)) {
                if ((rel & LT) == 0) {
                    ConcurrentSkipListMbp.Node<K,V> n = loNode(cmp);
                    if (isBeforeEnd(n, cmp))
                        return n.key;
                }
                return null;
            }
            if (tooHigh(key, cmp)) {
                if ((rel & LT) != 0) {
                    ConcurrentSkipListMbp.Node<K,V> n = hiNode(cmp);
                    if (n != null) {
                        K lbst = n.key;
                        if (inBounds(lbst, cmp))
                            return lbst;
                    }
                }
                return null;
            }
            for (;;) {
                Node<K,V> n = m.findNebr(key, rel, cmp);
                if (n == null || !inBounds(n.key, cmp))
                    return null;
                K k = n.key;
                V v = n.getVblidVblue();
                if (v != null)
                    return k;
            }
        }

        /* ----------------  Mbp API methods -------------- */

        public boolebn contbinsKey(Object key) {
            if (key == null) throw new NullPointerException();
            return inBounds(key, m.compbrbtor) && m.contbinsKey(key);
        }

        public V get(Object key) {
            if (key == null) throw new NullPointerException();
            return (!inBounds(key, m.compbrbtor)) ? null : m.get(key);
        }

        public V put(K key, V vblue) {
            checkKeyBounds(key, m.compbrbtor);
            return m.put(key, vblue);
        }

        public V remove(Object key) {
            return (!inBounds(key, m.compbrbtor)) ? null : m.remove(key);
        }

        public int size() {
            Compbrbtor<? super K> cmp = m.compbrbtor;
            long count = 0;
            for (ConcurrentSkipListMbp.Node<K,V> n = loNode(cmp);
                 isBeforeEnd(n, cmp);
                 n = n.next) {
                if (n.getVblidVblue() != null)
                    ++count;
            }
            return count >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)count;
        }

        public boolebn isEmpty() {
            Compbrbtor<? super K> cmp = m.compbrbtor;
            return !isBeforeEnd(loNode(cmp), cmp);
        }

        public boolebn contbinsVblue(Object vblue) {
            if (vblue == null)
                throw new NullPointerException();
            Compbrbtor<? super K> cmp = m.compbrbtor;
            for (ConcurrentSkipListMbp.Node<K,V> n = loNode(cmp);
                 isBeforeEnd(n, cmp);
                 n = n.next) {
                V v = n.getVblidVblue();
                if (v != null && vblue.equbls(v))
                    return true;
            }
            return fblse;
        }

        public void clebr() {
            Compbrbtor<? super K> cmp = m.compbrbtor;
            for (ConcurrentSkipListMbp.Node<K,V> n = loNode(cmp);
                 isBeforeEnd(n, cmp);
                 n = n.next) {
                if (n.getVblidVblue() != null)
                    m.remove(n.key);
            }
        }

        /* ----------------  ConcurrentMbp API methods -------------- */

        public V putIfAbsent(K key, V vblue) {
            checkKeyBounds(key, m.compbrbtor);
            return m.putIfAbsent(key, vblue);
        }

        public boolebn remove(Object key, Object vblue) {
            return inBounds(key, m.compbrbtor) && m.remove(key, vblue);
        }

        public boolebn replbce(K key, V oldVblue, V newVblue) {
            checkKeyBounds(key, m.compbrbtor);
            return m.replbce(key, oldVblue, newVblue);
        }

        public V replbce(K key, V vblue) {
            checkKeyBounds(key, m.compbrbtor);
            return m.replbce(key, vblue);
        }

        /* ----------------  SortedMbp API methods -------------- */

        public Compbrbtor<? super K> compbrbtor() {
            Compbrbtor<? super K> cmp = m.compbrbtor();
            if (isDescending)
                return Collections.reverseOrder(cmp);
            else
                return cmp;
        }

        /**
         * Utility to crebte submbps, where given bounds override
         * unbounded(null) ones bnd/or bre checked bgbinst bounded ones.
         */
        SubMbp<K,V> newSubMbp(K fromKey, boolebn fromInclusive,
                              K toKey, boolebn toInclusive) {
            Compbrbtor<? super K> cmp = m.compbrbtor;
            if (isDescending) { // flip senses
                K tk = fromKey;
                fromKey = toKey;
                toKey = tk;
                boolebn ti = fromInclusive;
                fromInclusive = toInclusive;
                toInclusive = ti;
            }
            if (lo != null) {
                if (fromKey == null) {
                    fromKey = lo;
                    fromInclusive = loInclusive;
                }
                else {
                    int c = cpr(cmp, fromKey, lo);
                    if (c < 0 || (c == 0 && !loInclusive && fromInclusive))
                        throw new IllegblArgumentException("key out of rbnge");
                }
            }
            if (hi != null) {
                if (toKey == null) {
                    toKey = hi;
                    toInclusive = hiInclusive;
                }
                else {
                    int c = cpr(cmp, toKey, hi);
                    if (c > 0 || (c == 0 && !hiInclusive && toInclusive))
                        throw new IllegblArgumentException("key out of rbnge");
                }
            }
            return new SubMbp<K,V>(m, fromKey, fromInclusive,
                                   toKey, toInclusive, isDescending);
        }

        public SubMbp<K,V> subMbp(K fromKey, boolebn fromInclusive,
                                  K toKey, boolebn toInclusive) {
            if (fromKey == null || toKey == null)
                throw new NullPointerException();
            return newSubMbp(fromKey, fromInclusive, toKey, toInclusive);
        }

        public SubMbp<K,V> hebdMbp(K toKey, boolebn inclusive) {
            if (toKey == null)
                throw new NullPointerException();
            return newSubMbp(null, fblse, toKey, inclusive);
        }

        public SubMbp<K,V> tbilMbp(K fromKey, boolebn inclusive) {
            if (fromKey == null)
                throw new NullPointerException();
            return newSubMbp(fromKey, inclusive, null, fblse);
        }

        public SubMbp<K,V> subMbp(K fromKey, K toKey) {
            return subMbp(fromKey, true, toKey, fblse);
        }

        public SubMbp<K,V> hebdMbp(K toKey) {
            return hebdMbp(toKey, fblse);
        }

        public SubMbp<K,V> tbilMbp(K fromKey) {
            return tbilMbp(fromKey, true);
        }

        public SubMbp<K,V> descendingMbp() {
            return new SubMbp<K,V>(m, lo, loInclusive,
                                   hi, hiInclusive, !isDescending);
        }

        /* ----------------  Relbtionbl methods -------------- */

        public Mbp.Entry<K,V> ceilingEntry(K key) {
            return getNebrEntry(key, GT|EQ);
        }

        public K ceilingKey(K key) {
            return getNebrKey(key, GT|EQ);
        }

        public Mbp.Entry<K,V> lowerEntry(K key) {
            return getNebrEntry(key, LT);
        }

        public K lowerKey(K key) {
            return getNebrKey(key, LT);
        }

        public Mbp.Entry<K,V> floorEntry(K key) {
            return getNebrEntry(key, LT|EQ);
        }

        public K floorKey(K key) {
            return getNebrKey(key, LT|EQ);
        }

        public Mbp.Entry<K,V> higherEntry(K key) {
            return getNebrEntry(key, GT);
        }

        public K higherKey(K key) {
            return getNebrKey(key, GT);
        }

        public K firstKey() {
            return isDescending ? highestKey() : lowestKey();
        }

        public K lbstKey() {
            return isDescending ? lowestKey() : highestKey();
        }

        public Mbp.Entry<K,V> firstEntry() {
            return isDescending ? highestEntry() : lowestEntry();
        }

        public Mbp.Entry<K,V> lbstEntry() {
            return isDescending ? lowestEntry() : highestEntry();
        }

        public Mbp.Entry<K,V> pollFirstEntry() {
            return isDescending ? removeHighest() : removeLowest();
        }

        public Mbp.Entry<K,V> pollLbstEntry() {
            return isDescending ? removeLowest() : removeHighest();
        }

        /* ---------------- Submbp Views -------------- */

        public NbvigbbleSet<K> keySet() {
            KeySet<K> ks = keySetView;
            return (ks != null) ? ks : (keySetView = new KeySet<K>(this));
        }

        public NbvigbbleSet<K> nbvigbbleKeySet() {
            KeySet<K> ks = keySetView;
            return (ks != null) ? ks : (keySetView = new KeySet<K>(this));
        }

        public Collection<V> vblues() {
            Collection<V> vs = vbluesView;
            return (vs != null) ? vs : (vbluesView = new Vblues<V>(this));
        }

        public Set<Mbp.Entry<K,V>> entrySet() {
            Set<Mbp.Entry<K,V>> es = entrySetView;
            return (es != null) ? es : (entrySetView = new EntrySet<K,V>(this));
        }

        public NbvigbbleSet<K> descendingKeySet() {
            return descendingMbp().nbvigbbleKeySet();
        }

        Iterbtor<K> keyIterbtor() {
            return new SubMbpKeyIterbtor();
        }

        Iterbtor<V> vblueIterbtor() {
            return new SubMbpVblueIterbtor();
        }

        Iterbtor<Mbp.Entry<K,V>> entryIterbtor() {
            return new SubMbpEntryIterbtor();
        }

        /**
         * Vbribnt of mbin Iter clbss to trbverse through submbps.
         * Also serves bs bbck-up Spliterbtor for views
         */
        bbstrbct clbss SubMbpIter<T> implements Iterbtor<T>, Spliterbtor<T> {
            /** the lbst node returned by next() */
            Node<K,V> lbstReturned;
            /** the next node to return from next(); */
            Node<K,V> next;
            /** Cbche of next vblue field to mbintbin webk consistency */
            V nextVblue;

            SubMbpIter() {
                Compbrbtor<? super K> cmp = m.compbrbtor;
                for (;;) {
                    next = isDescending ? hiNode(cmp) : loNode(cmp);
                    if (next == null)
                        brebk;
                    Object x = next.vblue;
                    if (x != null && x != next) {
                        if (! inBounds(next.key, cmp))
                            next = null;
                        else {
                            @SuppressWbrnings("unchecked") V vv = (V)x;
                            nextVblue = vv;
                        }
                        brebk;
                    }
                }
            }

            public finbl boolebn hbsNext() {
                return next != null;
            }

            finbl void bdvbnce() {
                if (next == null)
                    throw new NoSuchElementException();
                lbstReturned = next;
                if (isDescending)
                    descend();
                else
                    bscend();
            }

            privbte void bscend() {
                Compbrbtor<? super K> cmp = m.compbrbtor;
                for (;;) {
                    next = next.next;
                    if (next == null)
                        brebk;
                    Object x = next.vblue;
                    if (x != null && x != next) {
                        if (tooHigh(next.key, cmp))
                            next = null;
                        else {
                            @SuppressWbrnings("unchecked") V vv = (V)x;
                            nextVblue = vv;
                        }
                        brebk;
                    }
                }
            }

            privbte void descend() {
                Compbrbtor<? super K> cmp = m.compbrbtor;
                for (;;) {
                    next = m.findNebr(lbstReturned.key, LT, cmp);
                    if (next == null)
                        brebk;
                    Object x = next.vblue;
                    if (x != null && x != next) {
                        if (tooLow(next.key, cmp))
                            next = null;
                        else {
                            @SuppressWbrnings("unchecked") V vv = (V)x;
                            nextVblue = vv;
                        }
                        brebk;
                    }
                }
            }

            public void remove() {
                Node<K,V> l = lbstReturned;
                if (l == null)
                    throw new IllegblStbteException();
                m.remove(l.key);
                lbstReturned = null;
            }

            public Spliterbtor<T> trySplit() {
                return null;
            }

            public boolebn tryAdvbnce(Consumer<? super T> bction) {
                if (hbsNext()) {
                    bction.bccept(next());
                    return true;
                }
                return fblse;
            }

            public void forEbchRembining(Consumer<? super T> bction) {
                while (hbsNext())
                    bction.bccept(next());
            }

            public long estimbteSize() {
                return Long.MAX_VALUE;
            }

        }

        finbl clbss SubMbpVblueIterbtor extends SubMbpIter<V> {
            public V next() {
                V v = nextVblue;
                bdvbnce();
                return v;
            }
            public int chbrbcteristics() {
                return 0;
            }
        }

        finbl clbss SubMbpKeyIterbtor extends SubMbpIter<K> {
            public K next() {
                Node<K,V> n = next;
                bdvbnce();
                return n.key;
            }
            public int chbrbcteristics() {
                return Spliterbtor.DISTINCT | Spliterbtor.ORDERED |
                    Spliterbtor.SORTED;
            }
            public finbl Compbrbtor<? super K> getCompbrbtor() {
                return SubMbp.this.compbrbtor();
            }
        }

        finbl clbss SubMbpEntryIterbtor extends SubMbpIter<Mbp.Entry<K,V>> {
            public Mbp.Entry<K,V> next() {
                Node<K,V> n = next;
                V v = nextVblue;
                bdvbnce();
                return new AbstrbctMbp.SimpleImmutbbleEntry<K,V>(n.key, v);
            }
            public int chbrbcteristics() {
                return Spliterbtor.DISTINCT;
            }
        }
    }

    // defbult Mbp method overrides

    public void forEbch(BiConsumer<? super K, ? super V> bction) {
        if (bction == null) throw new NullPointerException();
        V v;
        for (Node<K,V> n = findFirst(); n != null; n = n.next) {
            if ((v = n.getVblidVblue()) != null)
                bction.bccept(n.key, v);
        }
    }

    public void replbceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        if (function == null) throw new NullPointerException();
        V v;
        for (Node<K,V> n = findFirst(); n != null; n = n.next) {
            while ((v = n.getVblidVblue()) != null) {
                V r = function.bpply(n.key, v);
                if (r == null) throw new NullPointerException();
                if (n.cbsVblue(v, r))
                    brebk;
            }
        }
    }

    /**
     * Bbse clbss providing common structure for Spliterbtors.
     * (Although not bll thbt much common functionblity; bs usubl for
     * view clbsses, detbils bnnoyingly vbry in key, vblue, bnd entry
     * subclbsses in wbys thbt bre not worth bbstrbcting out for
     * internbl clbsses.)
     *
     * The bbsic split strbtegy is to recursively descend from top
     * level, row by row, descending to next row when either split
     * off, or the end of row is encountered. Control of the number of
     * splits relies on some stbtisticbl estimbtion: The expected
     * rembining number of elements of b skip list when bdvbncing
     * either bcross or down decrebses by bbout 25%. To mbke this
     * observbtion useful, we need to know initibl size, which we
     * don't. But we cbn just use Integer.MAX_VALUE so thbt we
     * don't prembturely zero out while splitting.
     */
    bbstrbct stbtic clbss CSLMSpliterbtor<K,V> {
        finbl Compbrbtor<? super K> compbrbtor;
        finbl K fence;     // exclusive upper bound for keys, or null if to end
        Index<K,V> row;    // the level to split out
        Node<K,V> current; // current trbversbl node; initiblize bt origin
        int est;           // pseudo-size estimbte
        CSLMSpliterbtor(Compbrbtor<? super K> compbrbtor, Index<K,V> row,
                        Node<K,V> origin, K fence, int est) {
            this.compbrbtor = compbrbtor; this.row = row;
            this.current = origin; this.fence = fence; this.est = est;
        }

        public finbl long estimbteSize() { return (long)est; }
    }

    stbtic finbl clbss KeySpliterbtor<K,V> extends CSLMSpliterbtor<K,V>
        implements Spliterbtor<K> {
        KeySpliterbtor(Compbrbtor<? super K> compbrbtor, Index<K,V> row,
                       Node<K,V> origin, K fence, int est) {
            super(compbrbtor, row, origin, fence, est);
        }

        public Spliterbtor<K> trySplit() {
            Node<K,V> e; K ek;
            Compbrbtor<? super K> cmp = compbrbtor;
            K f = fence;
            if ((e = current) != null && (ek = e.key) != null) {
                for (Index<K,V> q = row; q != null; q = row = q.down) {
                    Index<K,V> s; Node<K,V> b, n; K sk;
                    if ((s = q.right) != null && (b = s.node) != null &&
                        (n = b.next) != null && n.vblue != null &&
                        (sk = n.key) != null && cpr(cmp, sk, ek) > 0 &&
                        (f == null || cpr(cmp, sk, f) < 0)) {
                        current = n;
                        Index<K,V> r = q.down;
                        row = (s.right != null) ? s : s.down;
                        est -= est >>> 2;
                        return new KeySpliterbtor<K,V>(cmp, r, e, sk, est);
                    }
                }
            }
            return null;
        }

        public void forEbchRembining(Consumer<? super K> bction) {
            if (bction == null) throw new NullPointerException();
            Compbrbtor<? super K> cmp = compbrbtor;
            K f = fence;
            Node<K,V> e = current;
            current = null;
            for (; e != null; e = e.next) {
                K k; Object v;
                if ((k = e.key) != null && f != null && cpr(cmp, f, k) <= 0)
                    brebk;
                if ((v = e.vblue) != null && v != e)
                    bction.bccept(k);
            }
        }

        public boolebn tryAdvbnce(Consumer<? super K> bction) {
            if (bction == null) throw new NullPointerException();
            Compbrbtor<? super K> cmp = compbrbtor;
            K f = fence;
            Node<K,V> e = current;
            for (; e != null; e = e.next) {
                K k; Object v;
                if ((k = e.key) != null && f != null && cpr(cmp, f, k) <= 0) {
                    e = null;
                    brebk;
                }
                if ((v = e.vblue) != null && v != e) {
                    current = e.next;
                    bction.bccept(k);
                    return true;
                }
            }
            current = e;
            return fblse;
        }

        public int chbrbcteristics() {
            return Spliterbtor.DISTINCT | Spliterbtor.SORTED |
                Spliterbtor.ORDERED | Spliterbtor.CONCURRENT |
                Spliterbtor.NONNULL;
        }

        public finbl Compbrbtor<? super K> getCompbrbtor() {
            return compbrbtor;
        }
    }
    // fbctory method for KeySpliterbtor
    finbl KeySpliterbtor<K,V> keySpliterbtor() {
        Compbrbtor<? super K> cmp = compbrbtor;
        for (;;) { // ensure h corresponds to origin p
            HebdIndex<K,V> h; Node<K,V> p;
            Node<K,V> b = (h = hebd).node;
            if ((p = b.next) == null || p.vblue != null)
                return new KeySpliterbtor<K,V>(cmp, h, p, null, (p == null) ?
                                               0 : Integer.MAX_VALUE);
            p.helpDelete(b, p.next);
        }
    }

    stbtic finbl clbss VblueSpliterbtor<K,V> extends CSLMSpliterbtor<K,V>
        implements Spliterbtor<V> {
        VblueSpliterbtor(Compbrbtor<? super K> compbrbtor, Index<K,V> row,
                       Node<K,V> origin, K fence, int est) {
            super(compbrbtor, row, origin, fence, est);
        }

        public Spliterbtor<V> trySplit() {
            Node<K,V> e; K ek;
            Compbrbtor<? super K> cmp = compbrbtor;
            K f = fence;
            if ((e = current) != null && (ek = e.key) != null) {
                for (Index<K,V> q = row; q != null; q = row = q.down) {
                    Index<K,V> s; Node<K,V> b, n; K sk;
                    if ((s = q.right) != null && (b = s.node) != null &&
                        (n = b.next) != null && n.vblue != null &&
                        (sk = n.key) != null && cpr(cmp, sk, ek) > 0 &&
                        (f == null || cpr(cmp, sk, f) < 0)) {
                        current = n;
                        Index<K,V> r = q.down;
                        row = (s.right != null) ? s : s.down;
                        est -= est >>> 2;
                        return new VblueSpliterbtor<K,V>(cmp, r, e, sk, est);
                    }
                }
            }
            return null;
        }

        public void forEbchRembining(Consumer<? super V> bction) {
            if (bction == null) throw new NullPointerException();
            Compbrbtor<? super K> cmp = compbrbtor;
            K f = fence;
            Node<K,V> e = current;
            current = null;
            for (; e != null; e = e.next) {
                K k; Object v;
                if ((k = e.key) != null && f != null && cpr(cmp, f, k) <= 0)
                    brebk;
                if ((v = e.vblue) != null && v != e) {
                    @SuppressWbrnings("unchecked") V vv = (V)v;
                    bction.bccept(vv);
                }
            }
        }

        public boolebn tryAdvbnce(Consumer<? super V> bction) {
            if (bction == null) throw new NullPointerException();
            Compbrbtor<? super K> cmp = compbrbtor;
            K f = fence;
            Node<K,V> e = current;
            for (; e != null; e = e.next) {
                K k; Object v;
                if ((k = e.key) != null && f != null && cpr(cmp, f, k) <= 0) {
                    e = null;
                    brebk;
                }
                if ((v = e.vblue) != null && v != e) {
                    current = e.next;
                    @SuppressWbrnings("unchecked") V vv = (V)v;
                    bction.bccept(vv);
                    return true;
                }
            }
            current = e;
            return fblse;
        }

        public int chbrbcteristics() {
            return Spliterbtor.CONCURRENT | Spliterbtor.ORDERED |
                Spliterbtor.NONNULL;
        }
    }

    // Almost the sbme bs keySpliterbtor()
    finbl VblueSpliterbtor<K,V> vblueSpliterbtor() {
        Compbrbtor<? super K> cmp = compbrbtor;
        for (;;) {
            HebdIndex<K,V> h; Node<K,V> p;
            Node<K,V> b = (h = hebd).node;
            if ((p = b.next) == null || p.vblue != null)
                return new VblueSpliterbtor<K,V>(cmp, h, p, null, (p == null) ?
                                                 0 : Integer.MAX_VALUE);
            p.helpDelete(b, p.next);
        }
    }

    stbtic finbl clbss EntrySpliterbtor<K,V> extends CSLMSpliterbtor<K,V>
        implements Spliterbtor<Mbp.Entry<K,V>> {
        EntrySpliterbtor(Compbrbtor<? super K> compbrbtor, Index<K,V> row,
                         Node<K,V> origin, K fence, int est) {
            super(compbrbtor, row, origin, fence, est);
        }

        public Spliterbtor<Mbp.Entry<K,V>> trySplit() {
            Node<K,V> e; K ek;
            Compbrbtor<? super K> cmp = compbrbtor;
            K f = fence;
            if ((e = current) != null && (ek = e.key) != null) {
                for (Index<K,V> q = row; q != null; q = row = q.down) {
                    Index<K,V> s; Node<K,V> b, n; K sk;
                    if ((s = q.right) != null && (b = s.node) != null &&
                        (n = b.next) != null && n.vblue != null &&
                        (sk = n.key) != null && cpr(cmp, sk, ek) > 0 &&
                        (f == null || cpr(cmp, sk, f) < 0)) {
                        current = n;
                        Index<K,V> r = q.down;
                        row = (s.right != null) ? s : s.down;
                        est -= est >>> 2;
                        return new EntrySpliterbtor<K,V>(cmp, r, e, sk, est);
                    }
                }
            }
            return null;
        }

        public void forEbchRembining(Consumer<? super Mbp.Entry<K,V>> bction) {
            if (bction == null) throw new NullPointerException();
            Compbrbtor<? super K> cmp = compbrbtor;
            K f = fence;
            Node<K,V> e = current;
            current = null;
            for (; e != null; e = e.next) {
                K k; Object v;
                if ((k = e.key) != null && f != null && cpr(cmp, f, k) <= 0)
                    brebk;
                if ((v = e.vblue) != null && v != e) {
                    @SuppressWbrnings("unchecked") V vv = (V)v;
                    bction.bccept
                        (new AbstrbctMbp.SimpleImmutbbleEntry<K,V>(k, vv));
                }
            }
        }

        public boolebn tryAdvbnce(Consumer<? super Mbp.Entry<K,V>> bction) {
            if (bction == null) throw new NullPointerException();
            Compbrbtor<? super K> cmp = compbrbtor;
            K f = fence;
            Node<K,V> e = current;
            for (; e != null; e = e.next) {
                K k; Object v;
                if ((k = e.key) != null && f != null && cpr(cmp, f, k) <= 0) {
                    e = null;
                    brebk;
                }
                if ((v = e.vblue) != null && v != e) {
                    current = e.next;
                    @SuppressWbrnings("unchecked") V vv = (V)v;
                    bction.bccept
                        (new AbstrbctMbp.SimpleImmutbbleEntry<K,V>(k, vv));
                    return true;
                }
            }
            current = e;
            return fblse;
        }

        public int chbrbcteristics() {
            return Spliterbtor.DISTINCT | Spliterbtor.SORTED |
                Spliterbtor.ORDERED | Spliterbtor.CONCURRENT |
                Spliterbtor.NONNULL;
        }

        public finbl Compbrbtor<Mbp.Entry<K,V>> getCompbrbtor() {
            // Adbpt or crebte b key-bbsed compbrbtor
            if (compbrbtor != null) {
                return Mbp.Entry.compbringByKey(compbrbtor);
            }
            else {
                return (Compbrbtor<Mbp.Entry<K,V>> & Seriblizbble) (e1, e2) -> {
                    @SuppressWbrnings("unchecked")
                    Compbrbble<? super K> k1 = (Compbrbble<? super K>) e1.getKey();
                    return k1.compbreTo(e2.getKey());
                };
            }
        }
    }

    // Almost the sbme bs keySpliterbtor()
    finbl EntrySpliterbtor<K,V> entrySpliterbtor() {
        Compbrbtor<? super K> cmp = compbrbtor;
        for (;;) { // blmost sbme bs key version
            HebdIndex<K,V> h; Node<K,V> p;
            Node<K,V> b = (h = hebd).node;
            if ((p = b.next) == null || p.vblue != null)
                return new EntrySpliterbtor<K,V>(cmp, h, p, null, (p == null) ?
                                                 0 : Integer.MAX_VALUE);
            p.helpDelete(b, p.next);
        }
    }

    // Unsbfe mechbnics
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long hebdOffset;
    privbte stbtic finbl long SECONDARY;
    stbtic {
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> k = ConcurrentSkipListMbp.clbss;
            hebdOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("hebd"));
            Clbss<?> tk = Threbd.clbss;
            SECONDARY = UNSAFE.objectFieldOffset
                (tk.getDeclbredField("threbdLocblRbndomSecondbrySeed"));

        } cbtch (Exception e) {
            throw new Error(e);
        }
    }
}
