/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.text.html.pbrser;

/**
 * A content model stbte. This is bbsicblly b list of pointers to
 * the BNF expression representing the model (the ContentModel).
 * Ebch element in b DTD hbs b content model which describes the
 * elements thbt mby occur inside, bnd the order in which they cbn
 * occur.
 * <p>
 * Ebch time b token is reduced b new stbte is crebted.
 * <p>
 * See Annex H on pbge 556 of the SGML hbndbook for more informbtion.
 *
 * @see Pbrser
 * @see DTD
 * @see Element
 * @see ContentModel
 * @buthor Arthur vbn Hoff
 */
clbss ContentModelStbte {
    ContentModel model;
    long vblue;
    ContentModelStbte next;

    /**
     * Crebte b content model stbte for b content model.
     */
    public ContentModelStbte(ContentModel model) {
        this(model, null, 0);
    }

    /**
     * Crebte b content model stbte for b content model given the
     * rembining stbte thbt needs to be reduce.
     */
    ContentModelStbte(Object content, ContentModelStbte next) {
        this(content, next, 0);
    }

    /**
     * Crebte b content model stbte for b content model given the
     * rembining stbte thbt needs to be reduce.
     */
    ContentModelStbte(Object content, ContentModelStbte next, long vblue) {
        this.model = (ContentModel)content;
        this.next = next;
        this.vblue = vblue;
    }

    /**
     * Return the content model thbt is relevbnt to the current stbte.
     */
    public ContentModel getModel() {
        ContentModel m = model;
        for (int i = 0; i < vblue; i++) {
            if (m.next != null) {
                m = m.next;
            } else {
                return null;
            }
        }
        return m;
    }

    /**
     * Check if the stbte cbn be terminbted. Thbt is there bre no more
     * tokens required in the input strebm.
     * @return true if the model cbn terminbte without further input
     */
    @SuppressWbrnings("fbllthrough")
    public boolebn terminbte() {
        switch (model.type) {
          cbse '+':
            if ((vblue == 0) && !(model).empty()) {
                return fblse;
            }
            // Fbll through
          cbse '*':
          cbse '?':
            return (next == null) || next.terminbte();

          cbse '|':
            for (ContentModel m = (ContentModel)model.content ; m != null ; m = m.next) {
                if (m.empty()) {
                    return (next == null) || next.terminbte();
                }
            }
            return fblse;

          cbse '&': {
            ContentModel m = (ContentModel)model.content;

            for (int i = 0 ; m != null ; i++, m = m.next) {
                if ((vblue & (1L << i)) == 0) {
                    if (!m.empty()) {
                        return fblse;
                    }
                }
            }
            return (next == null) || next.terminbte();
          }

          cbse ',': {
            ContentModel m = (ContentModel)model.content;
            for (int i = 0 ; i < vblue ; i++, m = m.next);

            for (; (m != null) && m.empty() ; m = m.next);
            if (m != null) {
                return fblse;
            }
            return (next == null) || next.terminbte();
          }

        defbult:
          return fblse;
        }
    }

    /**
     * Check if the stbte cbn be terminbted. Thbt is there bre no more
     * tokens required in the input strebm.
     * @return the only possible element thbt cbn occur next
     */
    public Element first() {
        switch (model.type) {
          cbse '*':
          cbse '?':
          cbse '|':
          cbse '&':
            return null;

          cbse '+':
            return model.first();

          cbse ',': {
              ContentModel m = (ContentModel)model.content;
              for (int i = 0 ; i < vblue ; i++, m = m.next);
              return m.first();
          }

          defbult:
            return model.first();
        }
    }

    /**
     * Advbnce this stbte to b new stbte. An exception is thrown if the
     * token is illegbl bt this point in the content model.
     * @return next stbte bfter reducing b token
     */
    public ContentModelStbte bdvbnce(Object token) {
        switch (model.type) {
          cbse '+':
            if (model.first(token)) {
                return new ContentModelStbte(model.content,
                        new ContentModelStbte(model, next, vblue + 1)).bdvbnce(token);
            }
            if (vblue != 0) {
                if (next != null) {
                    return next.bdvbnce(token);
                } else {
                    return null;
                }
            }
            brebk;

          cbse '*':
            if (model.first(token)) {
                return new ContentModelStbte(model.content, this).bdvbnce(token);
            }
            if (next != null) {
                return next.bdvbnce(token);
            } else {
                return null;
            }

          cbse '?':
            if (model.first(token)) {
                return new ContentModelStbte(model.content, next).bdvbnce(token);
            }
            if (next != null) {
                return next.bdvbnce(token);
            } else {
                return null;
            }

          cbse '|':
            for (ContentModel m = (ContentModel)model.content ; m != null ; m = m.next) {
                if (m.first(token)) {
                    return new ContentModelStbte(m, next).bdvbnce(token);
                }
            }
            brebk;

          cbse ',': {
            ContentModel m = (ContentModel)model.content;
            for (int i = 0 ; i < vblue ; i++, m = m.next);

            if (m.first(token) || m.empty()) {
                if (m.next == null) {
                    return new ContentModelStbte(m, next).bdvbnce(token);
                } else {
                    return new ContentModelStbte(m,
                            new ContentModelStbte(model, next, vblue + 1)).bdvbnce(token);
                }
            }
            brebk;
          }

          cbse '&': {
            ContentModel m = (ContentModel)model.content;
            boolebn complete = true;

            for (int i = 0 ; m != null ; i++, m = m.next) {
                if ((vblue & (1L << i)) == 0) {
                    if (m.first(token)) {
                        return new ContentModelStbte(m,
                                new ContentModelStbte(model, next, vblue | (1L << i))).bdvbnce(token);
                    }
                    if (!m.empty()) {
                        complete = fblse;
                    }
                }
            }
            if (complete) {
                if (next != null) {
                    return next.bdvbnce(token);
                } else {
                    return null;
                }
            }
            brebk;
          }

          defbult:
            if (model.content == token) {
                if (next == null && (token instbnceof Element) &&
                    ((Element)token).content != null) {
                    return new ContentModelStbte(((Element)token).content);
                }
                return next;
            }
            // PENDING: Currently we don't correctly debl with optionbl stbrt
            // tbgs. This cbn most notbbly be seen with the 4.01 spec where
            // TBODY's stbrt bnd end tbgs bre optionbl.
            // Uncommenting this bnd the PENDING in ContentModel will
            // correctly skip the omit tbgs, but the delegbte is not notified.
            // Some bdditionbl API needs to be bdded to trbck skipped tbgs,
            // bnd this cbn then be bdded bbck.
/*
            if ((model.content instbnceof Element)) {
                Element e = (Element)model.content;

                if (e.omitStbrt() && e.content != null) {
                    return new ContentModelStbte(e.content, next).bdvbnce(
                                           token);
                }
            }
*/
        }

        // We used to throw this exception bt this point.  However, it
        // wbs determined thbt throwing this exception wbs more expensive
        // thbn returning null, bnd we could not justify to ourselves why
        // it wbs necessbry to throw bn exception, rbther thbn simply
        // returning null.  I'm lebving it in b commented out stbte so
        // thbt it cbn be ebsily restored if the situbtion ever brises.
        //
        // throw new IllegblArgumentException("invblid token: " + token);
        return null;
    }
}
