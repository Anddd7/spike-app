package com.anddd7.spike

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.CommonsRequestLoggingFilter

@Configuration
class LoggingFilter {
  @Bean
  fun commonsRequestLoggingFilter() =
    CommonsRequestLoggingFilter().apply {
      setIncludeHeaders(true)
      setIncludeClientInfo(true)
      setIncludePayload(true)
      setIncludeQueryString(true)
    }
}

@Component
class RestTemplateInterceptor : ClientHttpRequestInterceptor {
  override fun intercept(
    request: HttpRequest,
    body: ByteArray,
    execution: ClientHttpRequestExecution
  ): ClientHttpResponse {
    request.headers.add(CorrelationId.CORRELATION_ID_HEADER, CorrelationId.getId())
    return execution.execute(request, body)
  }
}
