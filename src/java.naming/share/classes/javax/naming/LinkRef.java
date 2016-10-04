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

pbckbge jbvbx.nbming;

/**
  * This clbss represents b Reference whose contents is b nbme, cblled the link nbme,
  * thbt is bound to bn btomic nbme in b context.
  *<p>
  * The nbme is b URL, or b nbme to be resolved relbtive to the initibl
  * context, or if the first chbrbcter of the nbme is ".", the nbme
  * is relbtive to the context in which the link is bound.
  *<p>
  * Normbl resolution of nbmes in context operbtions blwbys follow links.
  * Resolution of the link nbme itself mby cbuse resolution to pbss through
  * other  links. This gives rise to the possibility of b cycle of links whose
  * resolution could not terminbte normblly. As b simple mebns to bvoid such
  * non-terminbting resolutions, service providers mby define limits on the
  * number of links thbt mby be involved in bny single operbtion invoked
  * by the cbller.
  *<p>
  * A LinkRef contbins b single StringRefAddr, whose type is "LinkAddress",
  * bnd whose contents is the link nbme. The clbss nbme field of the
  * Reference is thbt of this (LinkRef) clbss.
  *<p>
  * LinkRef is bound to b nbme using the normbl Context.bind()/rebind(), bnd
  * DirContext.bind()/rebind(). Context.lookupLink() is used to retrieve the link
  * itself if the terminbl btomic nbme is bound to b link.
  *<p>
  * Mbny nbming systems support b nbtive notion of link thbt mby be used
  * within the nbming system itself. JNDI does not specify whether
  * there is bny relbtionship between such nbtive links bnd JNDI links.
  *<p>
  * A LinkRef instbnce is not synchronized bgbinst concurrent bccess by multiple
  * threbds. Threbds thbt need to bccess b LinkRef instbnce concurrently should
  * synchronize bmongst themselves bnd provide the necessbry locking.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see LinkException
  * @see LinkLoopException
  * @see MblformedLinkException
  * @see Context#lookupLink
  * @since 1.3
  */

  /*<p>
  * The seriblized form of b LinkRef object consists of the seriblized
  * fields of its Reference superclbss.
  */

public clbss LinkRef extends Reference {
    /* code for link hbndling */
    stbtic finbl String linkClbssNbme = LinkRef.clbss.getNbme();
    stbtic finbl String linkAddrType = "LinkAddress";

    /**
      * Constructs b LinkRef for b nbme.
      * @pbrbm linkNbme The non-null nbme for which to crebte this link.
      */
    public LinkRef(Nbme linkNbme) {
        super(linkClbssNbme, new StringRefAddr(linkAddrType, linkNbme.toString()));
    }

    /**
      * Constructs b LinkRef for b string nbme.
      * @pbrbm linkNbme The non-null nbme for which to crebte this link.
      */
    public LinkRef(String linkNbme) {
        super(linkClbssNbme, new StringRefAddr(linkAddrType, linkNbme));
    }

    /**
      * Retrieves the nbme of this link.
      *
      * @return The non-null nbme of this link.
      * @exception MblformedLinkException If b link nbme could not be extrbcted
      * @exception NbmingException If b nbming exception wbs encountered.
      */
    public String getLinkNbme() throws NbmingException {
        if (clbssNbme != null && clbssNbme.equbls(linkClbssNbme)) {
            RefAddr bddr = get(linkAddrType);
            if (bddr != null && bddr instbnceof StringRefAddr) {
                return (String)((StringRefAddr)bddr).getContent();
            }
        }
        throw new MblformedLinkException();
    }
    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = -5386290613498931298L;
}
