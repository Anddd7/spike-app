package com.anddd7.spike

import com.anddd7.spike.CorrelationId.CORRELATION_ID_HEADER
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import java.util.UUID
import javax.servlet.FilterChain
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CorrelationIdFilter : HttpFilter() {
  private val log = LoggerFactory.getLogger(this.javaClass)

  override fun doFilter(
    request: HttpServletRequest,
    response: HttpServletResponse,
    chain: FilterChain
  ) {
    val correlationId = request.getHeader(CORRELATION_ID_HEADER) ?: UUID.randomUUID().toString()

    CorrelationId.setId(correlationId);
    MDC.put("correlation_id", correlationId)
    log.debug("current correlation id: {}", correlationId)

    response.addHeader(CORRELATION_ID_HEADER, correlationId)

    chain.doFilter(request, response)
  }
}


object CorrelationId {
  const val CORRELATION_ID_HEADER = "X-CORRELATION-ID"

  private val id = ThreadLocal<String>()

  fun setId(value: String) = id.set(value)
  fun getId(): String = id.get()
}
