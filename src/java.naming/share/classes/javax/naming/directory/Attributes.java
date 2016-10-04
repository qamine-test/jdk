/*
 * Copyright (c) 1999, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.nbming.directory;

import jbvb.util.Hbshtbble;
import jbvb.util.Enumerbtion;

import jbvbx.nbming.NbmingException;
import jbvbx.nbming.NbmingEnumerbtion;

/**
  * This interfbce represents b collection of bttributes.
  *<p>
  * In b directory, nbmed objects cbn hbve bssocibted with them
  * bttributes.  The Attributes interfbce represents b collection of bttributes.
  * For exbmple, you cbn request from the directory the bttributes
  * bssocibted with bn object.  Those bttributes bre returned in
  * bn object thbt implements the Attributes interfbce.
  *<p>
  * Attributes in bn object thbt implements the  Attributes interfbce bre
  * unordered. The object cbn hbve zero or more bttributes.
  * Attributes is either cbse-sensitive or cbse-insensitive (cbse-ignore).
  * This property is determined bt the time the Attributes object is
  * crebted. (see BbsicAttributes constructor for exbmple).
  * In b cbse-insensitive Attributes, the cbse of its bttribute identifiers
  * is ignored when sebrching for bn bttribute, or bdding bttributes.
  * In b cbse-sensitive Attributes, the cbse is significbnt.
  *<p>
  * Note thbt updbtes to Attributes (such bs bdding or removing bn bttribute)
  * do not bffect the corresponding representbtion in the directory.
  * Updbtes to the directory cbn only be effected
  * using operbtions in the DirContext interfbce.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see DirContext#getAttributes
  * @see DirContext#modifyAttributes
  * @see DirContext#bind
  * @see DirContext#rebind
  * @see DirContext#crebteSubcontext
  * @see DirContext#sebrch
  * @see BbsicAttributes
  * @since 1.3
  */

public interfbce Attributes extends Clonebble, jbvb.io.Seriblizbble {
    /**
      * Determines whether the bttribute set ignores the cbse of
      * bttribute identifiers when retrieving or bdding bttributes.
      * @return true if cbse is ignored; fblse otherwise.
      */
    boolebn isCbseIgnored();

    /**
      * Retrieves the number of bttributes in the bttribute set.
      *
      * @return The nonnegbtive number of bttributes in this bttribute set.
      */
    int size();

    /**
      * Retrieves the bttribute with the given bttribute id from the
      * bttribute set.
      *
      * @pbrbm bttrID The non-null id of the bttribute to retrieve.
      *           If this bttribute set ignores the chbrbcter
      *           cbse of its bttribute ids, the cbse of bttrID
      *           is ignored.
      * @return The bttribute identified by bttrID; null if not found.
      * @see #put
      * @see #remove
      */
    Attribute get(String bttrID);

    /**
      * Retrieves bn enumerbtion of the bttributes in the bttribute set.
      * The effects of updbtes to this bttribute set on this enumerbtion
      * bre undefined.
      *
      * @return A non-null enumerbtion of the bttributes in this bttribute set.
      *         Ebch element of the enumerbtion is of clbss <tt>Attribute</tt>.
      *         If bttribute set hbs zero bttributes, bn empty enumerbtion
      *         is returned.
      */
    NbmingEnumerbtion<? extends Attribute> getAll();

    /**
      * Retrieves bn enumerbtion of the ids of the bttributes in the
      * bttribute set.
      * The effects of updbtes to this bttribute set on this enumerbtion
      * bre undefined.
      *
      * @return A non-null enumerbtion of the bttributes' ids in
      *         this bttribute set. Ebch element of the enumerbtion is
      *         of clbss String.
      *         If bttribute set hbs zero bttributes, bn empty enumerbtion
      *         is returned.
      */
    NbmingEnumerbtion<String> getIDs();

    /**
      * Adds b new bttribute to the bttribute set.
      *
      * @pbrbm bttrID   non-null The id of the bttribute to bdd.
      *           If the bttribute set ignores the chbrbcter
      *           cbse of its bttribute ids, the cbse of bttrID
      *           is ignored.
      * @pbrbm vbl      The possibly null vblue of the bttribute to bdd.
      *                 If null, the bttribute does not hbve bny vblues.
      * @return The Attribute with bttrID thbt wbs previous in this bttribute set;
      *         null if no such bttribute existed.
      * @see #remove
      */
    Attribute put(String bttrID, Object vbl);

    /**
      * Adds b new bttribute to the bttribute set.
      *
      * @pbrbm bttr     The non-null bttribute to bdd.
      *                 If the bttribute set ignores the chbrbcter
      *                 cbse of its bttribute ids, the cbse of
      *                 bttr's identifier is ignored.
      * @return The Attribute with the sbme ID bs bttr thbt wbs previous
      *         in this bttribute set;
      *         null if no such bttribute existed.
      * @see #remove
      */
    Attribute put(Attribute bttr);

    /**
      * Removes the bttribute with the bttribute id 'bttrID' from
      * the bttribute set. If the bttribute does not exist, ignore.
      *
      * @pbrbm bttrID   The non-null id of the bttribute to remove.
      *                 If the bttribute set ignores the chbrbcter
      *                 cbse of its bttribute ids, the cbse of
      *                 bttrID is ignored.
      * @return The Attribute with the sbme ID bs bttrID thbt wbs previous
      *         in the bttribute set;
      *         null if no such bttribute existed.
      */
    Attribute remove(String bttrID);

    /**
      * Mbkes b copy of the bttribute set.
      * The new set contbins the sbme bttributes bs the originbl set:
      * the bttributes bre not themselves cloned.
      * Chbnges to the copy will not bffect the originbl bnd vice versb.
      *
      * @return A non-null copy of this bttribute set.
      */
    Object clone();

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    // stbtic finbl long seriblVersionUID = -7247874645443605347L;
}
